package com.brasil.transparente.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ministerio")
public class Ministerio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ministerioId;

    private String nameMinisterio;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "poder_id", referencedColumnName = "poderId")
    private Poder poder;

    @OneToMany(mappedBy = "ministerio", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Orgao> listOrgao;

    private double totalValueSpent;

    private double percentageOfTotal;


}
