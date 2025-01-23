package com.example.facturacion;

import com.example.facturacion.model.dtos.FacturaRequestDto;
import com.example.facturacion.repositories.FacturacionRepository;
import com.example.facturacion.services.FacturacionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

// Registramos como aplicación Spring Boot
@SpringBootApplication
@EnableScheduling
public class FacturacionApplication {

//	private final FacturacionService facturacionService;
//	private final FacturacionRepository facturacionRepository; // Inyectar el repositorio

	// Constructor para la inyección de dependencias
//	public FacturacionApplication(FacturacionService facturacionService, FacturacionRepository facturacionRepository) {
//		this.facturacionService = facturacionService;
//        this.facturacionRepository = facturacionRepository;
//    }

	public static void main(String[] args) {
		SpringApplication.run(FacturacionApplication.class, args);
	}

//	@Override
//	public void run(String... args) {
//		// Crear un objeto FacturaRequestDto con el campo requerido y
//		FacturaRequestDto facturaRequestDto = new FacturaRequestDto("corriente",facturacionRepository.findUltimoIdDispositivo());
//
//		// Ejecutar la solicitud y manejar la respuesta reactiva
//		facturacionService.infFacturar(facturaRequestDto)
//				.doOnSuccess(resultado -> System.out.println("Resultado recibido: " + resultado)) // En caso de éxito
//				.doOnError(error -> System.err.println("Error encontrado: " + error.getMessage())) // En caso de error
//				.subscribe(); // Suscribimos para ejecutar el flujo
//	}
}