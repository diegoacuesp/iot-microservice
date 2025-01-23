package com.example.facturacion.repositories;

import com.example.facturacion.model.entities.Facturacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FacturacionRepository extends JpaRepository <Facturacion, Long> {
    @Query("SELECT MAX(f.idDispositivo) FROM Facturacion f")
    Long findUltimoIdDispositivo();

    @Query("SELECT MAX(f.idDispositivo) FROM Facturacion f WHERE f.nombreVariable = :nombreVariable")
    Long findMaxIdByNombreVariable(@Param("nombreVariable") String nombreVariable);
}
