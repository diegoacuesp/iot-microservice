package com.example.Dispositivo.model;

public record DispositivoResponseDto(
        String nombreVariable,
        String numeroSerie,
        String estampaTiempo,
        Double valorVariable
) {
}
