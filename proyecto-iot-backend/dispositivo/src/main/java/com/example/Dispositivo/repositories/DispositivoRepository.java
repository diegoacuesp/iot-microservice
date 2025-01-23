package com.example.Dispositivo.repositories;

import com.example.Dispositivo.model.Dispositivo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    List<Dispositivo> findByNombreVariable(String nombreVariable);
    List<Dispositivo> findByNombreVariableAndIdGreaterThan(String nombreVariable, Long id);

}
