package com.brasil.transparente.api.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "despesa_simplificada")
@Schema(description = "Simplified and reclassified public spending record, grouped according to the project methodology to aggregate comparable social expenditures.")
public class DespesaSimplificada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the simplified spending record")
    private Long despesaSimplificadaId;

    @Schema(description = "Name of the reclassified spending category, such as Health, Education, or Social Security")
    private String name;

    @Schema(description = "Total amount effectively paid for this category, in BRL")
    private double totalValue;

    @Schema(description = "Percentage of the total spending of the federative unit represented by this category")
    private double percentageOfTotal;

    @Schema(description = "Federative Unit identifier to which this spending record belongs. Use 1 for the Brazilian Federal Government")
    private Long unidadeFederativaId;
}