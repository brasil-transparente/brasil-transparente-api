package com.brasil.transparente.api.service

import com.brasil.transparente.api.dto.DisplayableElementDTO
import com.brasil.transparente.api.entity.*
import com.brasil.transparente.api.repository.*
import com.brasil.transparente.api.util.MapperService
import com.brasil.transparente.api.util.OrdererService
import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class FinderServiceTest {

    @Mock
    private lateinit var poderRepository: PoderRepository

    @Mock
    private lateinit var ministerioRepository: MinisterioRepository

    @Mock
    private lateinit var orgaoRepository: OrgaoRepository

    @Mock
    private lateinit var unidadeGestoraRepository: UnidadeGestoraRepository

    @Mock
    private lateinit var elementoDespesaRepository: ElementoDespesaRepository

    @Mock
    private lateinit var unidadeFederativaRepository: UnidadeFederativaRepository

    @Mock
    private lateinit var despesaSimplificadaRepository: DespesaSimplificadaRepository

    @Mock
    private lateinit var ordererService: OrdererService

    @Mock
    private lateinit var mapperService: MapperService

    @InjectMocks
    private lateinit var finderService: FinderService

    @Test
    fun `getPoderesByUnidadeFederativa should return list of DisplayableElementDTO with level 0`() {
        val ufId = 1L
        val poder = Poder().apply {
            poderId = 10L
            namePoder = "Executivo"
            totalValueSpent = 1000.0
            percentageOfTotal = 50.0
        }
        val dto = DisplayableElementDTO(10L, "Executivo", 1000.0, 50.0, 0)

        `when`(poderRepository.findByUnidadeFederativaUnidadeFederativaId(ufId)).thenReturn(listOf(poder))
        `when`(mapperService.mapToDisplayableElementDto(10L, "Executivo", 1000.0, 50.0, 0)).thenReturn(dto)

        val result = finderService.getPoderesByUnidadeFederativa(ufId)

        assertEquals(1, result.size)
        assertEquals(dto, result.first())
        verify(poderRepository).findByUnidadeFederativaUnidadeFederativaId(ufId)
        verify(mapperService).mapToDisplayableElementDto(10L, "Executivo", 1000.0, 50.0, 0)
    }

    @Test
    fun `getMinisterioByPoderId should return list of DisplayableElementDTO with level 1`() {
        val poderId = 10L
        val ministerio = Ministerio().apply {
            ministerioId = 20L
            nameMinisterio = "Ministério da Saúde"
            totalValueSpent = 500.0
            percentageOfTotal = 25.0
        }
        val dto = DisplayableElementDTO(20L, "Ministério da Saúde", 500.0, 25.0, 1)

        `when`(ministerioRepository.findByPoderPoderId(poderId)).thenReturn(listOf(ministerio))
        `when`(mapperService.mapToDisplayableElementDto(20L, "Ministério da Saúde", 500.0, 25.0, 1)).thenReturn(dto)

        val result = finderService.getMinisterioByPoderId(poderId)

        assertEquals(1, result.size)
        assertEquals(dto, result.first())
        verify(ministerioRepository).findByPoderPoderId(poderId)
        verify(mapperService).mapToDisplayableElementDto(20L, "Ministério da Saúde", 500.0, 25.0, 1)
    }

    @Test
    fun `getOrgaoByMinisterioId should return list of DisplayableElementDTO when multiple orgaos exist`() {
        val ministerioId = 20L
        val orgao1 = Orgao().apply {
            orgaoId = 30L
            nameOrgao = "Órgão A"
            totalValueSpent = 200.0
            percentageOfTotal = 10.0
        }
        val orgao2 = Orgao().apply {
            orgaoId = 31L
            nameOrgao = "Órgão B"
            totalValueSpent = 300.0
            percentageOfTotal = 15.0
        }
        val dto1 = DisplayableElementDTO(30L, "Órgão A", 200.0, 10.0, 2)
        val dto2 = DisplayableElementDTO(31L, "Órgão B", 300.0, 15.0, 2)

        `when`(orgaoRepository.findByMinisterioMinisterioId(ministerioId)).thenReturn(listOf(orgao1, orgao2))
        `when`(mapperService.mapToDisplayableElementDto(30L, "Órgão A", 200.0, 10.0, 2)).thenReturn(dto1)
        `when`(mapperService.mapToDisplayableElementDto(31L, "Órgão B", 300.0, 15.0, 2)).thenReturn(dto2)

        val result = finderService.getOrgaoByMinisterioId(ministerioId)

        assertEquals(2, result.size)
        assertEquals(listOf(dto1, dto2), result)
    }

    @Test
    fun `getOrgaoByMinisterioId should delegate to getUnidadeGestoraByOrgaoId when single orgao exists`() {
        val ministerioId = 20L
        val orgaoId = 30L
        val orgao = Orgao().apply {
            this.orgaoId = orgaoId
            nameOrgao = "Órgão Único"
            totalValueSpent = 200.0
            percentageOfTotal = 10.0
        }

        `when`(orgaoRepository.findByMinisterioMinisterioId(ministerioId)).thenReturn(listOf(orgao))
        `when`(unidadeGestoraRepository.findByOrgaoOrgaoId(orgaoId)).thenReturn(emptyList())

        val result = finderService.getOrgaoByMinisterioId(ministerioId)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `getUnidadeGestoraByOrgaoId should return Unidades Gestoras when multiple exist`() {
        val orgaoId = 30L
        val ug1 = UnidadeGestora().apply {
            unidadeGestoraId = 40L
            nameUnidadeGestora = "UG 1"
            totalValueSpent = 100.0
            percentageOfTotal = 5.0
        }
        val ug2 = UnidadeGestora().apply {
            unidadeGestoraId = 41L
            nameUnidadeGestora = "UG 2"
            totalValueSpent = 100.0
            percentageOfTotal = 5.0
        }
        val dto1 = DisplayableElementDTO(40L, "UG 1", 100.0, 5.0, 3)
        val dto2 = DisplayableElementDTO(41L, "UG 2", 100.0, 5.0, 3)

        `when`(unidadeGestoraRepository.findByOrgaoOrgaoId(orgaoId)).thenReturn(listOf(ug1, ug2))
        `when`(mapperService.mapToDisplayableElementDto(40L, "UG 1", 100.0, 5.0, 3)).thenReturn(dto1)
        `when`(mapperService.mapToDisplayableElementDto(41L, "UG 2", 100.0, 5.0, 3)).thenReturn(dto2)

        val result = finderService.getUnidadeGestoraByOrgaoId(orgaoId)

        assertEquals(2, result.size)
        assertEquals(listOf(dto1, dto2), result)
    }

    @Test
    fun `getUnidadeGestoraByOrgaoId should return Elementos de Despesa when single UG matches Orgao name`() {
        val orgaoId = 30L
        val sameName = "Mesmo Nome"
        val ug = UnidadeGestora().apply {
            unidadeGestoraId = 40L
            nameUnidadeGestora = sameName
            totalValueSpent = 100.0
            percentageOfTotal = 5.0
        }
        val orgao = Orgao().apply {
            this.orgaoId = orgaoId
            nameOrgao = sameName
        }
        val elemento = ElementoDespesa().apply {
            elementoDespesaId = 50L
            nameElementoDespesa = "Elemento X"
            totalValueSpent = 100.0
            percentageOfTotal = 5.0
        }
        val elementoDto = DisplayableElementDTO(50L, "Elemento X", 100.0, 5.0, 4)

        `when`(unidadeGestoraRepository.findByOrgaoOrgaoId(orgaoId)).thenReturn(listOf(ug))
        `when`(orgaoRepository.findById(orgaoId.toString())).thenReturn(Optional.of(orgao))
        `when`(elementoDespesaRepository.findByUnidadeGestoraUnidadeGestoraId(40L)).thenReturn(listOf(elemento))
        `when`(mapperService.mapToDisplayableElementDto(50L, "Elemento X", 100.0, 5.0, 4)).thenReturn(elementoDto)

        val result = finderService.getUnidadeGestoraByOrgaoId(orgaoId)

        assertEquals(1, result.size)
        assertEquals(elementoDto, result.first())
    }

    @Test
    fun `getUnidadeGestoraByOrgaoId should return UnidadeGestora DTO when single UG does not match Orgao name`() {
        val orgaoId = 30L
        val ug = UnidadeGestora().apply {
            unidadeGestoraId = 40L
            nameUnidadeGestora = "Nome UG"
            totalValueSpent = 100.0
            percentageOfTotal = 5.0
        }
        val orgao = Orgao().apply {
            this.orgaoId = orgaoId
            nameOrgao = "Nome Orgao Diferente"
        }
        val ugDto = DisplayableElementDTO(40L, "Nome UG", 100.0, 5.0, 3)

        `when`(unidadeGestoraRepository.findByOrgaoOrgaoId(orgaoId)).thenReturn(listOf(ug))
        `when`(orgaoRepository.findById(orgaoId.toString())).thenReturn(Optional.of(orgao))
        `when`(mapperService.mapToDisplayableElementDto(40L, "Nome UG", 100.0, 5.0, 3)).thenReturn(ugDto)

        val result = finderService.getUnidadeGestoraByOrgaoId(orgaoId)

        assertEquals(1, result.size)
        assertEquals(ugDto, result.first())
    }

    @Test
    fun `getUnidadeGestoraByOrgaoId should throw EntityNotFoundException when Orgao is not found`() {
        val orgaoId = 30L
        val ug = UnidadeGestora().apply {
            unidadeGestoraId = 40L
            nameUnidadeGestora = "Nome UG"
        }

        `when`(unidadeGestoraRepository.findByOrgaoOrgaoId(orgaoId)).thenReturn(listOf(ug))
        `when`(orgaoRepository.findById(orgaoId.toString())).thenReturn(Optional.empty())

        val exception = assertThrows<EntityNotFoundException> {
            finderService.getUnidadeGestoraByOrgaoId(orgaoId)
        }

        assertEquals("Órgão não encontrado: 30", exception.message)
    }

    @Test
    fun `getElementoDespesaByUnidadeGestoraId should return list of DisplayableElementDTO with level 4`() {
        val ugId = 40L
        val elemento = ElementoDespesa().apply {
            elementoDespesaId = 50L
            nameElementoDespesa = "Elemento Y"
            totalValueSpent = 150.0
            percentageOfTotal = 7.5
        }
        val dto = DisplayableElementDTO(50L, "Elemento Y", 150.0, 7.5, 4)

        `when`(elementoDespesaRepository.findByUnidadeGestoraUnidadeGestoraId(ugId)).thenReturn(listOf(elemento))
        `when`(mapperService.mapToDisplayableElementDto(50L, "Elemento Y", 150.0, 7.5, 4)).thenReturn(dto)

        val result = finderService.getElementoDespesaByUnidadeGestoraId(ugId)

        assertEquals(1, result.size)
        assertEquals(dto, result.first())
    }

    @Test
    fun `getDespesaSimplificada should return ordered list of DespesaSimplificada`() {
        val ufId = 1L
        val despesa = DespesaSimplificada()
        val despesaList = mutableListOf(despesa)

        `when`(despesaSimplificadaRepository.findByUnidadeFederativa(ufId)).thenReturn(despesaList)

        val result = finderService.getDespesaSimplificada(ufId)

        assertEquals(despesaList, result)
        verify(ordererService).orderDespesaSimplificadaListBySpending(despesaList)
    }

    @Test
    fun `getTotalValueSpentByUnidadeFederativaId should return total value spent`() {
        val ufId = 1L
        val expectedTotal = 150000.0

        `when`(unidadeFederativaRepository.findTotalValueSpentByUnidadeFederativaId(ufId)).thenReturn(expectedTotal)

        val result = finderService.getTotalValueSpentByUnidadeFederativaId(ufId)

        assertEquals(expectedTotal, result)
        verify(unidadeFederativaRepository).findTotalValueSpentByUnidadeFederativaId(ufId)
    }
}