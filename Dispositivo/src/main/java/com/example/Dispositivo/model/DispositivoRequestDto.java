package com.example.Dispositivo.model;

public record DispositivoRequestDto(
        String nombreVariable,
        String numeroSerie,
        String estampaTiempo,
        Double valorVariable
) {
}
