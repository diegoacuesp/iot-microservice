package com.example.Dispositivo.controllers;


import com.example.Dispositivo.model.DispositivoRequestDto;
import com.example.Dispositivo.model.DispositivoResponseDto;
import com.example.Dispositivo.model.FacturaRequestDto;
import com.example.Dispositivo.services.DispositivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/dispositivo")
@RequiredArgsConstructor
public class DispositivoController {

    private final DispositivoService dispositivoService;

    //Metodo post para recibir mediciones del dispositivo cliente externo
    @PostMapping("/envio")
    @ResponseStatus(HttpStatus.CREATED)
    public DispositivoResponseDto recepcionDispositivo(@RequestBody DispositivoRequestDto dispositivoRequestDto) {
        // TODO: Implement logic for processing dispositivoRequest
        return this.dispositivoService.guardarDispositivo(dispositivoRequestDto);
    }

    //Metodo Post para la extracción de información mediante WebFlux
    @PostMapping("/solicitud")
    @ResponseStatus(HttpStatus.OK)
    public List<DispositivoResponseDto> envioDispositivo(@RequestBody FacturaRequestDto facturaRequestDto){

        //Implementa logica que procesa consulta a la BD y devuelve la consulta por WebFlux
       return this.dispositivoService.findByVariableNameAndIdGreaterThan(facturaRequestDto);
    }
}
