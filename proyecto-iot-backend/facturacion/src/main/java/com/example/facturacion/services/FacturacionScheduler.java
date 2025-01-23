package com.example.facturacion.services;

import com.example.facturacion.model.dtos.FacturaRequestDto;
import com.example.facturacion.repositories.FacturacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FacturacionScheduler {

    private final FacturacionService facturacionService;
    private final FacturacionRepository facturacionRepository;
    private final TablaDinamicaService tablaDinamicaService;

    // Define la lista de nombres para las variables a procesar en el bucle, que son las variables que necesitamos como finalmente disponibles en nuestra aplicación
    private final List<String> variables = Arrays.asList("corriente", "voltaje");

    // Ejecuta la tarea cada 60 segundos
    @Scheduled(fixedRate = 6000) // 60,000 ms = 60 segundos
    public void ejecutarFacturacion() {

        // Iterar sobre cada nombre en la lista
        for (String nombreVariable : variables) {
            // Obtener el ID máximo para los registros donde nombreVariable coincide
            Long maxId = facturacionRepository.findMaxIdByNombreVariable(nombreVariable);

            // Usar un valor predeterminado si no se encuentra ningún ID (manejo de null)
            if (maxId == null) {
                maxId = 0L; // Valor inicial
            }

            System.out.println("Maximo idDispositivo encontrado para " + nombreVariable + ": " + maxId);

            // Crear el DTO de solicitud con el nombre y el ID máximo obtenido
            FacturaRequestDto facturaRequestDto = new FacturaRequestDto(nombreVariable, maxId);

            // Ejecutar la solicitud al servicio y manejar la respuesta
            facturacionService.infFacturar(facturaRequestDto)
                    .doOnSuccess(resultado -> System.out.println("Resultado para " + nombreVariable + ": " + resultado)) // Éxito
                    .doOnError(error -> System.err.println("Error para " + nombreVariable + ": " + error.getMessage())) // Error
                    .subscribe(); // Ejecutar el flujo reactivo
        }

        // Creamos y movemos registros a tablas independientes
        // Iterar sobre cada nombre en la lista
        for (String nombreVariable : variables) {
            try {
                // Crear tabla dinámica
                tablaDinamicaService.crearTabla(nombreVariable);

                // Mover registros a la tabla creada
                tablaDinamicaService.moverRegistrosAFila(nombreVariable, nombreVariable);

//                System.out.println("La tabla '%s' se creó y los registros fueron movidos correctamente."+ nombreVariable);
            } catch (Exception e) {
                System.out.println( "Error: " + e.getMessage());
            }
        }


    }




}
