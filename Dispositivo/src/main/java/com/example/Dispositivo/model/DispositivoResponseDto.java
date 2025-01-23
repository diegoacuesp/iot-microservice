package com.example.Dispositivo.model;

public record DispositivoResponseDto(
//        Long id,
        Long idDispositivo,
        String nombreVariable,
        String numeroSerie,
        String estampaTiempo,
        Double valorVariable
) {
}
