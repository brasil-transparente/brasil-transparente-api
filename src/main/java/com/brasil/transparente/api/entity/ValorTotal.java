package com.brasil.transparente.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValorTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unidadeFederativaId;
    private String ano;
    private Double valorTotal;

    public ValorTotal(Long unidadeFederativaId, String ano, Double valorTotal) {
        this.unidadeFederativaId = unidadeFederativaId;
        this.ano = ano;
        this.valorTotal = valorTotal;
    }

}
