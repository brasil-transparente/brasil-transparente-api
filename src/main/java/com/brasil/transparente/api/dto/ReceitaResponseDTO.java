package com.brasil.transparente.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class ReceitaResponseDTO {
    private double valorTotal;
    private List<DisplayableElementDTO> receitas;
}
