package com.brasil.transparente.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "unidade_gestora")
public class UnidadeGestora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unidadeGestoraId;

    private String nameUnidadeGestora;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "orgao_id", referencedColumnName = "orgaoId")
    private Orgao orgao;

    @JsonManagedReference
    @OneToMany(mappedBy = "unidadeGestora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElementoDespesa> listElementDespesa;

    private double totalValueSpent;

    private double percentageOfTotal;

}
