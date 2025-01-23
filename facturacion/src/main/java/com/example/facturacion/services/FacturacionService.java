package com.example.facturacion.services;

import com.example.facturacion.model.dtos.FacturaRequestDto;
import com.example.facturacion.model.dtos.DispositivoResponseDto;
import com.example.facturacion.model.entities.Facturacion;
import com.example.facturacion.repositories.FacturacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FacturacionService {

    private final WebClient.Builder webClientBuilder;
    private final FacturacionRepository facturacionRepository; // Inyectar el repositorio

    public Mono<String> infFacturar(FacturaRequestDto facturaRequestDto) {
        return this.webClientBuilder.build()
                .post()
                .uri("http://localhost:8083/api/dispositivo/solicitud")
                .bodyValue(facturaRequestDto) // Pasamos el body de la solicitud
                .retrieve() // Recuperamos el resultado
                .bodyToFlux(DispositivoResponseDto.class) // Cambiar a `bodyToFlux` para arrays
                .collectList() // Convertir el flujo en una lista
                .cache() // Hace que el flujo sea "caliente" y compartido evitando multiples suscriptores y que se ejecute mas de una vez accidentalmente
                .flatMap(dispositivos -> {
                    if (dispositivos == null || dispositivos.isEmpty()) {
                        return Mono.error(new RuntimeException("No se encontraron dispositivos para la solicitud."));
                    }

                    // Iterar sobre cada dispositivo, convertirlo a Facturacion y guardarlo
                    dispositivos.forEach(dispositivo -> {
                        Facturacion facturacion = new Facturacion();
                        facturacion.setIdDispositivo(dispositivo.idDispositivo());
                        facturacion.setNombreVariable(dispositivo.nombreVariable());
                        facturacion.setNumeroSerie(dispositivo.numeroSerie());
                        facturacion.setEstampaTiempo(dispositivo.estampaTiempo());
                        facturacion.setValorVariable(dispositivo.valorVariable());

                        // Guardar la entidad en la base de datos
                        facturacionRepository.save(facturacion);
                    });


                    // Construimos unn string con los dispositivos recibidos
                    String resultado = dispositivos.stream()
                            .map(dispositivo -> String.format(
                                    "Dispositivo: %s, Serie: %s, Valor: %.2f",
                                    dispositivo.nombreVariable(),   // Usa getters
                                    dispositivo.numeroSerie(),
                                    dispositivo.valorVariable()))
                            .reduce((a, b) -> a + "; " + b) // Reducimos a una cadena única
                            .orElse(""); // Valor por defecto si está vacío
                    return Mono.just(resultado);
                })
                .onErrorResume(error -> Mono.error(new RuntimeException("Error al procesar la solicitud: " + error.getMessage())));
    }
 }
