package com.example.facturacion;

import com.example.facturacion.model.dtos.FacturaRequestDto;
import com.example.facturacion.services.FacturacionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Registramos como aplicación Spring Boot
@SpringBootApplication
public class FacturacionApplication implements CommandLineRunner {

	private final FacturacionService facturacionService;

	// Constructor para la inyección de dependencias
	public FacturacionApplication(FacturacionService facturacionService) {
		this.facturacionService = facturacionService;
	}

	public static void main(String[] args) {
		SpringApplication.run(FacturacionApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// Crear un objeto FacturaRequestDto con el campo requerido
		FacturaRequestDto facturaRequestDto = new FacturaRequestDto("corriente");

		// Ejecutar la solicitud y manejar la respuesta reactiva
		facturacionService.infFacturar(facturaRequestDto)
				.doOnSuccess(resultado -> System.out.println("Resultado recibido: " + resultado)) // En caso de éxito
				.doOnError(error -> System.err.println("Error encontrado: " + error.getMessage())) // En caso de error
				.subscribe(); // Suscribimos para ejecutar el flujo
	}
}