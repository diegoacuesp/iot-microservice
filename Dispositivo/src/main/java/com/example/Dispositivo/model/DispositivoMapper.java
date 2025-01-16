package com.example.Dispositivo.model;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DispositivoMapper {



    //Transformamos el objeto dispositivoRequestDto que viene del cliente al objeto Dispositivo entity
    public Dispositivo toDispositivo (DispositivoRequestDto dispositivoRequestDto){
        var dispositivo = new Dispositivo();
        dispositivo.setNombreVariable(dispositivoRequestDto.nombreVariable());
        dispositivo.setNumeroSerie(dispositivoRequestDto.numeroSerie());
        dispositivo.setEstampaTiempo(dispositivoRequestDto.estampaTiempo());
        dispositivo.setValorVariable(dispositivoRequestDto.valorVariable());

        return dispositivo;

    }

    //Transformamos el objeto Dispositivo entity a tipo DispositivoResponseDto que va al cliente
    public DispositivoResponseDto toDispositivoResponseDto ( Dispositivo dispositivo){
        return new DispositivoResponseDto(
                dispositivo.getNombreVariable(),
                dispositivo.getNumeroSerie(),
                dispositivo.getEstampaTiempo(),
                dispositivo.getValorVariable());
    }

    public DispositivoRequestDto jsonToDispositivoRequestDto(String mensajeJson) {

        var objectMapper = new ObjectMapper(); // Jackson para convertir JSON

        try {
            return objectMapper.readValue(mensajeJson, DispositivoRequestDto.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir mensaje MQTT a objeto: " + e.getMessage());
        }
    }
}
