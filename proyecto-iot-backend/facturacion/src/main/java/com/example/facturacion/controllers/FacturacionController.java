package com.example.facturacion.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facturacion")
@RequiredArgsConstructor
public class FacturacionController {


    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public void getfacturacion(@PathVariable("sku") String sku) {
        System.out.println("hola mundo");
    }

}
