package com.example.facturacion.model.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "facturacion")
@Data
@NoArgsConstructor
public class Facturacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idDispositivo;
    private String nombreVariable;
    private String numeroSerie;
    private String estampaTiempo;
    private Double valorVariable;

}
