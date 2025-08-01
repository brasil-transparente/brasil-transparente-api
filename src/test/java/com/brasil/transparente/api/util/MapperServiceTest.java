package com.brasil.transparente.api.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.entity.Ministerio;
import com.brasil.transparente.api.entity.Poder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class MapperServiceTest {

    private final MapperService mapperService = Mappers.getMapper(MapperService.class);

    @Test
    void testMappingToDisplayableElementoDto() {

        Ministerio ministerio = Ministerio.builder()
            .ministerioId(42L)
            .nameMinisterio("Ministério da Defesa")
            .totalValueSpent(500000)
            .percentageOfTotal(12.34)
            .build();

        int levelOfElement = 3;

        DisplayableElementDTO dto = mapperService.toDisplayableElementDTO(ministerio, levelOfElement);

        Assertions.assertThat(dto)
            .hasNoNullFieldsOrProperties()
            .satisfies(d -> {
                assertThat(d.getId()).isEqualTo(ministerio.getMinisterioId());
                assertThat(d.getName()).isEqualTo(ministerio.getNameMinisterio());
                assertThat(d.getTotalValueSpent()).isEqualTo(ministerio.getTotalValueSpent());
                assertThat(d.getPercentageOfTotal()).isEqualTo(ministerio.getPercentageOfTotal());
                assertThat(d.getLevel()).isEqualTo(levelOfElement);
            });
    }
}
