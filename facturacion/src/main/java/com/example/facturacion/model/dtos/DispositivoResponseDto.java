package com.example.facturacion.model.dtos;

public record DispositivoResponseDto(
        Long idDispositivo,
        String nombreVariable,
        String numeroSerie,
        String estampaTiempo,
        Double valorVariable
) {
}
