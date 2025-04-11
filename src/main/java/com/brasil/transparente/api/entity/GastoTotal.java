package com.brasil.transparente.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "gasto_total")
public class GastoTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gastoTotalId;

    private Double gastoTotalValue;

}
