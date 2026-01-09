package com.brasil.transparente.api.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "receita")
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receitaId;
    private String nameReceita;
    private Double totalValueSpent;
    private Double percentageOfTotal;

}
