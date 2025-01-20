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

    public List<DispositivoResponseDto> findByVariableName (FacturaRequestDto facturaRequestDto){
        String nombreVariable = facturaRequestDto.nombreVariable();

        // Consultar los registros desde el repositorio de dispositivo
        List<Dispositivo> dispositivos = dispositivoRepository.findByNombreVariable(nombreVariable);

        // Convertir a DTO
        return dispositivos.stream()
                .map(dispositivoMapper::toDispositivoResponseDto)
                .collect(Collectors.toList());

    }
}
