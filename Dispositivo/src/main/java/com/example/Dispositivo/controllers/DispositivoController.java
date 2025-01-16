package com.example.Dispositivo.controllers;


import com.example.Dispositivo.model.DispositivoRequestDto;
import com.example.Dispositivo.model.DispositivoResponseDto;
import com.example.Dispositivo.services.DispositivoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/dispositivo")
@RequiredArgsConstructor
public class DispositivoController {

    private final DispositivoService dispositivoService;

    //Metodo post para recibir mediciones del dispositivo cliente externo
    @PostMapping("/envio")
    @ResponseStatus(HttpStatus.CREATED)
    public DispositivoResponseDto recepciónDispositivo(@RequestBody DispositivoRequestDto dispositivoRequestDto) {
        // TODO: Implement logic for processing dispositivoRequest
        return this.dispositivoService.guardarDispositivo(dispositivoRequestDto);
    }
}
