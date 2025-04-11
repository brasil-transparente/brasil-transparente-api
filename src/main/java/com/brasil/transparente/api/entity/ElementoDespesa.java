package com.brasil.transparente.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "elemento_despesa")
public class ElementoDespesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long elementoDespesaId;

    private String nameElementoDespesa;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "unidade_gestora_id", referencedColumnName = "unidadeGestoraId")
    private UnidadeGestora unidadeGestora;

    private double totalValueSpent;

    private double percentageOfTotal;

}
