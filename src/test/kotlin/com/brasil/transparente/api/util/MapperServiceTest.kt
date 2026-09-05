package com.brasil.transparente.api.util

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull

class MapperServiceTest {

    @Test
    fun testMappingToDisplayableElementoDto() {
        val id = 42L
        val name = "Ministério da Defesa"
        val totalValueSpent = 500000.0
        val percentageOfTotal = 12.34
        val levelOfElement = 3

        val mapper = MapperService()
        val dto = mapper.mapToDisplayableElementDto(id, name, totalValueSpent, percentageOfTotal, levelOfElement)

        assertNotNull(dto)
        assertEquals(id, dto.id)
        assertEquals(name, dto.name)
        assertEquals(totalValueSpent, dto.totalValueSpent)
        assertEquals(percentageOfTotal, dto.percentageOfTotal)
        assertEquals(levelOfElement, dto.level)
    }
}