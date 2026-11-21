package com.brasil.transparente.api.util

import com.brasil.transparente.api.entity.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

class MapperServiceTest {

    private val mapper = MapperService()

    @Test
    fun testMappingToDisplayableElementoDto() {
        val id = 42L
        val name = "Ministério da Defesa"
        val totalValueSpent = 500000.0
        val percentageOfTotal = 12.34
        val levelOfElement = 3

        val dto = mapper.mapToDisplayableElementDto(id, name, totalValueSpent, percentageOfTotal, levelOfElement)

        assertNotNull(dto)
        assertEquals(id, dto.id)
        assertEquals(name, dto.name)
        assertEquals(totalValueSpent, dto.totalValueSpent)
        assertEquals(percentageOfTotal, dto.percentageOfTotal)
        assertEquals(levelOfElement, dto.level)
    }

    @Test
    fun shouldMapPoderToDto() {
        val poder = Poder().apply {
            poderId = 1L
            namePoder = "Executivo"
            totalValueSpent = 1000.0
            percentageOfTotal = 50.0
        }

        val dto = mapper.toDto(poder)

        assertEquals(1L, dto.id)
        assertEquals("Executivo", dto.name)
        assertEquals(1000.0, dto.totalValueSpent)
        assertEquals(50.0, dto.percentageOfTotal)
        assertEquals(MapperService.PODER_LEVEL, dto.level)
    }

    @Test
    fun shouldMapMinisterioToDto() {
        val ministerio = Ministerio().apply {
            ministerioId = 2L
            nameMinisterio = "Ministério da Educação"
            totalValueSpent = 2000.0
            percentageOfTotal = 25.0
        }

        val dto = mapper.toDto(ministerio)

        assertEquals(2L, dto.id)
        assertEquals("Ministério da Educação", dto.name)
        assertEquals(2000.0, dto.totalValueSpent)
        assertEquals(25.0, dto.percentageOfTotal)
        assertEquals(MapperService.MINISTERIO_LEVEL, dto.level)
    }

    @Test
    fun shouldMapOrgaoToDto() {
        val orgao = Orgao().apply {
            orgaoId = 3L
            nameOrgao = "Órgão Central"
            totalValueSpent = 300.0
            percentageOfTotal = 10.0
        }

        val dto = mapper.toDto(orgao)

        assertEquals(3L, dto.id)
        assertEquals("Órgão Central", dto.name)
        assertEquals(300.0, dto.totalValueSpent)
        assertEquals(10.0, dto.percentageOfTotal)
        assertEquals(MapperService.ORGAO_LEVEL, dto.level)
    }

    @Test
    fun shouldMapUnidadeGestoraToDto() {
        val ug = UnidadeGestora().apply {
            unidadeGestoraId = 4L
            nameUnidadeGestora = "UG Exemplo"
            totalValueSpent = 400.0
            percentageOfTotal = 5.0
        }

        val dto = mapper.toDto(ug)

        assertEquals(4L, dto.id)
        assertEquals("UG Exemplo", dto.name)
        assertEquals(400.0, dto.totalValueSpent)
        assertEquals(5.0, dto.percentageOfTotal)
        assertEquals(MapperService.UNIDADE_GESTORA_LEVEL, dto.level)
    }

    @Test
    fun shouldMapElementoDespesaToDto() {
        val elemento = ElementoDespesa().apply {
            elementoDespesaId = 5L
            nameElementoDespesa = "Diárias - Civil"
            totalValueSpent = 50.0
            percentageOfTotal = 1.0
        }

        val dto = mapper.toDto(elemento)

        assertEquals(5L, dto.id)
        assertEquals("Diárias - Civil", dto.name)
        assertEquals(50.0, dto.totalValueSpent)
        assertEquals(1.0, dto.percentageOfTotal)
        assertEquals(MapperService.ELEMENTO_DESPESA_LEVEL, dto.level)
    }
}