package com.example.facturacion.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TablaDinamicaService {

    private final EntityManager entityManager;

    public TablaDinamicaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void crearTabla(String nombreTabla) {
        // Verificar si la tabla ya existe
        String verificarTabla = String.format(
                "SELECT to_regclass('public.%s')", nombreTabla);
        Object resultado = entityManager.createNativeQuery(verificarTabla).getSingleResult();

        if (resultado == null) {
            // Crear la tabla si no existe
            String crearTablaSQL = String.format(
                    "CREATE TABLE IF NOT EXISTS %s (" +
                            "id SERIAL PRIMARY KEY, " +
                            "id_dispositivo BIGINT, " +
                            "nombre_variable VARCHAR(255), " +
                            "numero_serie VARCHAR(255), " +
                            "estampa_tiempo VARCHAR(255), " +
                            "valor_variable DOUBLE PRECISION, " +
                            "UNIQUE (id_dispositivo))",
                    nombreTabla);
            entityManager.createNativeQuery(crearTablaSQL).executeUpdate();
            System.out.println("La tabla " + nombreTabla + " se creó.");
        }
    }

    @Transactional
    public void moverRegistrosAFila(String nombreTabla, String nombreVariable) {
        try {
            // Intentar mover registros a la tabla recién creada
            String insertarRegistrosSQL = String.format(
                    "INSERT INTO %s (id_dispositivo, nombre_variable, numero_serie, estampa_tiempo, valor_variable) " +
                            "SELECT id_dispositivo, nombre_variable, numero_serie, estampa_tiempo, valor_variable " +
                            "FROM ( " +
                            "    SELECT DISTINCT ON (id_dispositivo) id_dispositivo, nombre_variable, numero_serie, estampa_tiempo, valor_variable " +
                            "    FROM facturacion " +
                            "    WHERE nombre_variable = :nombreVariable " +
                            "    ORDER BY id_dispositivo, estampa_tiempo DESC " +
                            ") AS subquery " +
                            "WHERE NOT EXISTS ( " +
                            "    SELECT 1 FROM %s t WHERE t.id_dispositivo = subquery.id_dispositivo " +
                            ")",
                    nombreTabla, nombreTabla);
            entityManager.createNativeQuery(insertarRegistrosSQL)
                    .setParameter("nombreVariable", nombreVariable)
                    .executeUpdate();
            System.out.println("Los registros fueron movidos correctamente." + nombreVariable);

            // Opcionalmente, eliminar los registros movidos
//            String eliminarRegistrosSQL = "DELETE FROM facturacion WHERE nombre_variable = :nombreVariable";
//            entityManager.createNativeQuery(eliminarRegistrosSQL)
//                    .setParameter("nombreVariable", nombreVariable)
//                    .executeUpdate();

        } catch (DataIntegrityViolationException e) {
            // Manejar errores de duplicados o integridad referencial
        }
    }
}