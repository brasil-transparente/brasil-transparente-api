package com.brasil.transparente.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
@Schema(description = "Generic element of the Brazilian administrative hierarchy. Depending on the endpoint context, it may represent a Power, Ministry, Agency, or Management Unit.")
public class DisplayableElementDTO {

    @Schema(description = "Unique identifier of the element")
    private Long id;
    @Schema(description = "Official name of the administrative entity")
    private String name;
    @Schema(description = "Total amount effectively paid in the analyzed period, in BRL")
    private Double totalValueSpent;
    @Schema(description = "Percentage of the parent level total represented by this element")
    private Double percentageOfTotal;
    @Schema(description = "Hierarchical level indicator: 1=Federative Unit, 2=Power, 3=Ministry, 4=Agency, 5=Management Unit")
    private Integer level;
}
