package com.brasil.transparente.api.util

import com.brasil.transparente.api.dto.DisplayableElementDTO
import com.brasil.transparente.api.entity.*
import org.springframework.stereotype.Service

@Service
class MapperService {

    companion object {
        const val PODER_LEVEL = 0
        const val MINISTERIO_LEVEL = 1
        const val ORGAO_LEVEL = 2
        const val UNIDADE_GESTORA_LEVEL = 3
        const val ELEMENTO_DESPESA_LEVEL = 4
    }

    fun toDto(poder: Poder): DisplayableElementDTO =
        DisplayableElementDTO(
            id = poder.poderId,
            name = poder.namePoder,
            totalValueSpent = poder.totalValueSpent,
            percentageOfTotal = poder.percentageOfTotal,
            level = PODER_LEVEL
        )

    fun toDto(ministerio: Ministerio): DisplayableElementDTO =
        DisplayableElementDTO(
            id = ministerio.ministerioId,
            name = ministerio.nameMinisterio,
            totalValueSpent = ministerio.totalValueSpent,
            percentageOfTotal = ministerio.percentageOfTotal,
            level = MINISTERIO_LEVEL
        )

    fun toDto(orgao: Orgao): DisplayableElementDTO =
        DisplayableElementDTO(
            id = orgao.orgaoId,
            name = orgao.nameOrgao,
            totalValueSpent = orgao.totalValueSpent,
            percentageOfTotal = orgao.percentageOfTotal,
            level = ORGAO_LEVEL
        )

    fun toDto(unidadeGestora: UnidadeGestora): DisplayableElementDTO =
        DisplayableElementDTO(
            id = unidadeGestora.unidadeGestoraId,
            name = unidadeGestora.nameUnidadeGestora,
            totalValueSpent = unidadeGestora.totalValueSpent,
            percentageOfTotal = unidadeGestora.percentageOfTotal,
            level = UNIDADE_GESTORA_LEVEL
        )

    fun toDto(elementoDespesa: ElementoDespesa): DisplayableElementDTO =
        DisplayableElementDTO(
            id = elementoDespesa.elementoDespesaId,
            name = elementoDespesa.nameElementoDespesa,
            totalValueSpent = elementoDespesa.totalValueSpent,
            percentageOfTotal = elementoDespesa.percentageOfTotal,
            level = ELEMENTO_DESPESA_LEVEL
        )

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
