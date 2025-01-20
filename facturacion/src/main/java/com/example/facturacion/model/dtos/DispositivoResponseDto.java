package com.example.facturacion.model.dtos;

public record DispositivoResponseDto(
        String nombreVariable,
        String numeroSerie,
        String estampaTiempo,
        Double valorVariable
) {
}
