package com.brasil.transparente.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "despesa_simplificada")
public class DespesaSimplificada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long despesaSimplificadaId;
    private String name;
    private double totalValue;
    private double percentageOfTotal;
    private Long unidadeFederativaId;

}
