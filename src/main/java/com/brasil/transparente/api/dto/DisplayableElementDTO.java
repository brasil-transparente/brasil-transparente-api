package com.brasil.transparente.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class DisplayableElementDTO {

    private Long id;
    private String name;
    private double totalValueSpent;
    private double percentageOfTotal;
    private int level;
}
