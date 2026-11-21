package com.brasil.transparente.api.service

import com.brasil.transparente.api.dto.DisplayableElementDTO
import com.brasil.transparente.api.entity.DespesaSimplificada
import com.brasil.transparente.api.entity.Poder
import com.brasil.transparente.api.repository.*
import com.brasil.transparente.api.util.MapperService
import com.brasil.transparente.api.util.OrdererService
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class FinderService(
    private val poderRepository: PoderRepository,
    private val ministerioRepository: MinisterioRepository,
    private val orgaoRepository: OrgaoRepository,
    private val unidadeGestoraRepository: UnidadeGestoraRepository,
    private val elementoDespesaRepository: ElementoDespesaRepository,
    private val despesaSimplificadaRepository: DespesaSimplificadaRepository,
    private val ordererService: OrdererService,
    private val mapperService: MapperService
) {

    companion object {
        private const val PODER_LEVEL = 0
        private const val MINISTERIO_LEVEL = 1
        private const val ORGAO_LEVEL = 2
        private const val UNIDADE_GESTORA_LEVEL = 3
        private const val ELEMENTO_DESPESA_LEVEL = 4
    }

    fun getPoderes(): List<DisplayableElementDTO> {
        val poderList: List<Poder> = poderRepository.findAll()
        val displayableElementDTOList = mutableListOf<DisplayableElementDTO>()
        for (poder in poderList) {
            displayableElementDTOList.add(
                mapperService.mapToDisplayableElementDto(
                    poder.poderId, poder.namePoder,
                    poder.totalValueSpent, poder.percentageOfTotal, PODER_LEVEL
                )
            )
        }
        return displayableElementDTOList
    }

    fun getMinisterioByPoderId(poderId: Long): List<DisplayableElementDTO> {
        val ministerioList = ministerioRepository.findByPoderPoderId(poderId)
        val displayableElementDTOList = mutableListOf<DisplayableElementDTO>()
        for (ministerio in ministerioList) {
            displayableElementDTOList.add(mapperService.mapToDisplayableElementDto(
                ministerio.ministerioId, ministerio.nameMinisterio, ministerio.totalValueSpent, ministerio.percentageOfTotal, MINISTERIO_LEVEL
            ))
        }
        return displayableElementDTOList
    }

    fun getOrgaoByMinisterioId(ministerioId: Long): List<DisplayableElementDTO> {
        val orgaoList = orgaoRepository.findByMinisterioMinisterioId(ministerioId)

        if (orgaoList.size == 1) {
            val orgaoId = requireNotNull(orgaoList.first().orgaoId) { "Órgão sem identificador" }
            val unidadeGestoraList = getUnidadeGestoraByOrgaoId(orgaoId)
            return if (unidadeGestoraList.size == 1 && orgaoList.first() == unidadeGestoraList.first()) {
                getElementoDespesaByUnidadeGestoraId(unidadeGestoraList.first().id)
            } else {
                unidadeGestoraList
            }
        }

        val displayableElementDTOList = mutableListOf<DisplayableElementDTO>()

        for (orgao in orgaoList) {
            displayableElementDTOList.add(
                mapperService.mapToDisplayableElementDto(
                    orgao.orgaoId, orgao.nameOrgao, orgao.totalValueSpent, orgao.percentageOfTotal, ORGAO_LEVEL
                )
            )
        }
        return displayableElementDTOList
    }

    fun getUnidadeGestoraByOrgaoId(orgaoId: Long): List<DisplayableElementDTO> {
        val unidadeGestoraList = unidadeGestoraRepository.findByOrgaoOrgaoId(orgaoId)

        if (unidadeGestoraList.size == 1) {
            val orgao = orgaoRepository.findById(orgaoId)
                .orElseThrow { EntityNotFoundException("Órgão não encontrado: $orgaoId") }
            if (orgao.nameOrgao == unidadeGestoraList.first().nameUnidadeGestora) {
                return getElementoDespesaByUnidadeGestoraId(unidadeGestoraList.first().unidadeGestoraId)
            }
        }

        val displayableElementDTOList = mutableListOf<DisplayableElementDTO>()
        for (unidadeGestora in unidadeGestoraList) {
            displayableElementDTOList.add(
                mapperService.mapToDisplayableElementDto(
                    unidadeGestora.unidadeGestoraId,
                    unidadeGestora.nameUnidadeGestora,
                    unidadeGestora.totalValueSpent,
                    unidadeGestora.percentageOfTotal,
                    UNIDADE_GESTORA_LEVEL
                )
            )
        }
        return displayableElementDTOList
    }

    fun getElementoDespesaByUnidadeGestoraId(unidadeGestoraId: Long?): List<DisplayableElementDTO> {
        val elementoDespesaList = elementoDespesaRepository.findByUnidadeGestoraUnidadeGestoraId(unidadeGestoraId)
        val displayableElementDTOList = mutableListOf<DisplayableElementDTO>()
        for (elementoDespesa in elementoDespesaList) {
            displayableElementDTOList.add(mapperService.mapToDisplayableElementDto(
                elementoDespesa.elementoDespesaId, elementoDespesa.nameElementoDespesa, elementoDespesa.totalValueSpent, elementoDespesa.percentageOfTotal, ELEMENTO_DESPESA_LEVEL
            ))
        }
        return displayableElementDTOList
    }

    fun getDespesaSimplificada(): List<DespesaSimplificada> {
        val despesaSimplificadaList = despesaSimplificadaRepository.findAll().toMutableList()
        ordererService.orderDespesaSimplificadaListBySpending(despesaSimplificadaList)
        return despesaSimplificadaList
    }

    fun getTotalValueSpent(): Double {
        return poderRepository.getTotalValueSpent()
    }

}



