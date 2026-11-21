package com.brasil.transparente.api.service

import com.brasil.transparente.api.dto.DisplayableElementDTO
import com.brasil.transparente.api.entity.DespesaSimplificada
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

    fun getPoderes(): List<DisplayableElementDTO> =
        poderRepository.findAll().map(mapperService::toDto)

    fun getMinisterioByPoderId(poderId: Long): List<DisplayableElementDTO> =
        ministerioRepository.findByPoderPoderId(poderId).map(mapperService::toDto)

    fun getOrgaoByMinisterioId(ministerioId: Long): List<DisplayableElementDTO> {
        val orgaoList = orgaoRepository.findByMinisterioMinisterioId(ministerioId)

        if (orgaoList.size == 1) {
            val orgaoId = requireNotNull(orgaoList.first().orgaoId) { "Órgão sem identificador" }
            val unidadeGestoraList = getUnidadeGestoraByOrgaoId(orgaoId)
            return if (unidadeGestoraList.size == 1 && orgaoList.first().nameOrgao == unidadeGestoraList.first().name) {
                getElementoDespesaByUnidadeGestoraId(unidadeGestoraList.first().id)
            } else {
                unidadeGestoraList
            }
        }

        return orgaoList.map(mapperService::toDto)
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

        return unidadeGestoraList.map(mapperService::toDto)
    }

    fun getElementoDespesaByUnidadeGestoraId(unidadeGestoraId: Long?): List<DisplayableElementDTO> =
        elementoDespesaRepository.findByUnidadeGestoraUnidadeGestoraId(unidadeGestoraId).map(mapperService::toDto)

    fun getDespesaSimplificada(): List<DespesaSimplificada> {
        val despesaSimplificadaList = despesaSimplificadaRepository.findAll().toMutableList()
        ordererService.orderDespesaSimplificadaListBySpending(despesaSimplificadaList)
        return despesaSimplificadaList
    }

    fun getTotalValueSpent(): Double =
        poderRepository.getTotalValueSpent()
}
