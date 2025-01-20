package com.example.facturacion.repositories;

import com.example.facturacion.model.entities.Facturacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturacionRepository extends JpaRepository <Facturacion, Long> {
}
