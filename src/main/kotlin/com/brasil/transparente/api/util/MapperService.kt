package com.brasil.transparente.api.util

import com.brasil.transparente.api.dto.DisplayableElementDTO
import org.springframework.stereotype.Service

@Service
class MapperService {

    fun mapToDisplayableElementDto(
        id: Long?,
        name: String?,
        totalValueSpent: Double?,
        percentageOfTotal: Double?,
        levelOfElement: Int?
    ): DisplayableElementDTO = DisplayableElementDTO(
            id = id,
            name = name,
            totalValueSpent = totalValueSpent,
            percentageOfTotal = percentageOfTotal,
            level = levelOfElement
        )

}
