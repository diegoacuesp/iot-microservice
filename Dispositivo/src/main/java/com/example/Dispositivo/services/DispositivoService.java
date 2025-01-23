package com.example.Dispositivo.services;

import com.example.Dispositivo.model.*;
import com.example.Dispositivo.repositories.DispositivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DispositivoService {
    private final DispositivoMapper dispositivoMapper;
    private final DispositivoRepository dispositivoRepository;



    public DispositivoResponseDto guardarDispositivo(DispositivoRequestDto dispositivoRequestDto ) {

            var dispositivo = dispositivoMapper.toDispositivo(dispositivoRequestDto);
            var savedDispositivo = dispositivoRepository.save(dispositivo);
            return dispositivoMapper.toDispositivoResponseDto(savedDispositivo);

    }

    public List<DispositivoResponseDto> findByVariableNameAndIdGreaterThan(FacturaRequestDto facturaRequestDto){
        String nombreVariable = facturaRequestDto.nombreVariable();
        Long ultimoId = facturaRequestDto.findUltimoIdDispositivo();

        // Consultar los registros que coincidan con el nombreVariable y cuyo Id sea mayor que findUltimoIdDispositivo

        List<Dispositivo> dispositivos;

        if (ultimoId == null) {
            // Si no hay filtro por findUltimoIdDispositivo, obtener todos los registros respecto al nombre de variable
            dispositivos = dispositivoRepository.findByNombreVariable(nombreVariable);
        } else {
            // Si hay filtro, buscar por nombreVariable e id mayor a findUltimoIdDispositivo
            dispositivos = dispositivoRepository.findByNombreVariableAndIdGreaterThan(nombreVariable, ultimoId);
        }


       // List<Dispositivo> dispositivos = dispositivoRepository.findByNombreVariableAndIdGreaterThan(nombreVariable, findUltimoIdDispositivo);


        // Convertir a DTO
        return dispositivos.stream()
                .map(dispositivoMapper::toDispositivoResponseDto)
                .collect(Collectors.toList());

    }
}
