package com.example.facturacion.model.dtos;

import com.example.facturacion.model.entities.Facturacion;

public class FacturacionMapper {

    //Transformamos el objeto dispositivoResponseDto que viene del cliente al objeto Facturacion entity
    public Facturacion toFacturacion (DispositivoResponseDto dispositivoResponseDto){
        var facturacion = new Facturacion();
        facturacion.setNombreVariable(dispositivoResponseDto.nombreVariable());
        facturacion.setNumeroSerie(dispositivoResponseDto.numeroSerie());
        facturacion.setEstampaTiempo(dispositivoResponseDto.estampaTiempo());
        facturacion .setValorVariable(dispositivoResponseDto.valorVariable());

        return facturacion;

    }
}
