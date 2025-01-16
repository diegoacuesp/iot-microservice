package com.example.Dispositivo.services;

import com.example.Dispositivo.model.DispositivoMapper;
import com.example.Dispositivo.model.DispositivoRequestDto;
import com.example.Dispositivo.model.DispositivoResponseDto;
import com.example.Dispositivo.repositories.DispositivoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
