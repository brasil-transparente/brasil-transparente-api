package com.brasil.transparente.api.util;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class MapperService {

    public DisplayableElementDTO mapToDisplayableElementDto(Long id, String name, double totalValueSpent, double percentageOfTotal, int levelOfElement) {
        return DisplayableElementDTO.builder()
                .id(id)
                .name(name)
                .totalValueSpent(totalValueSpent)
                .percentageOfTotal(percentageOfTotal)
                .level(levelOfElement)
                .build();
    }

}
