package com.example.facturacion.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispositivoListResponseDto {
   private List<DispositivoResponseDto> dispositivos;
}
