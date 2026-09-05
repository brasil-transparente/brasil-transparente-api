package com.brasil.transparente.api.controller

import com.brasil.transparente.api.dto.DisplayableElementDTO
import com.brasil.transparente.api.entity.DespesaSimplificada
import com.brasil.transparente.api.service.FinderService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.mockito.Mockito.`when`

@ExtendWith(MockitoExtension::class)
class FinderControllerTest {

    @Mock
    private lateinit var finderService: FinderService

    @InjectMocks
    private lateinit var finderController: FinderController

    private val displayableElementDTO = DisplayableElementDTO(
        id = 1L,
        name = "Test Name",
        totalValueSpent = 100.0,
        percentageOfTotal = 5.0,
        level = 1
    )

    @Test
    fun shouldReturnUnidadeGestoraList() {
        `when`(finderService.getUnidadeGestoraByOrgaoId(123L)).thenReturn(listOf(displayableElementDTO))

        val result = finderController.getUnidadeGestoraByOrgaoId(123L)

        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals("Test Name", result.first().name)
    }

    @Test
    fun shouldReturnPoderesByUnidadeFederativa() {
        `when`(finderService.getPoderesByUnidadeFederativa(99L)).thenReturn(listOf(displayableElementDTO))

        val result = finderController.getPoderesByUnidadeFederativa(99L)

        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals("Test Name", result.first().name)
    }

    @Test
    fun shouldReturnMinisteriosByPoderId() {
        `when`(finderService.getMinisterioByPoderId(88L)).thenReturn(listOf(displayableElementDTO))

        val result = finderController.getMinisterioByPoderId(88L)

        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals("Test Name", result.first().name)
    }

    @Test
    fun shouldReturnOrgaosByMinisterioId() {
        `when`(finderService.getOrgaoByMinisterioId(77L)).thenReturn(listOf(displayableElementDTO))

        val result = finderController.getOrgaoByMinisterioId(77L)

        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals("Test Name", result.first().name)
    }

    @Test
    fun shouldReturnElementoDespesaByUnidadeGestoraId() {
        `when`(finderService.getElementoDespesaByUnidadeGestoraId(66L)).thenReturn(listOf(displayableElementDTO))

        val result = finderController.getElementoDespesaByUnidadeGestoraId(66L)

        assertNotNull(result)
        assertEquals(1, result.size)
        assertEquals("Test Name", result.first().name)
    }

    @Test
    fun shouldReturnDespesaSimplificadaByUnidadeFederativaId() {
        val simplificada = DespesaSimplificada()

        `when`(finderService.getDespesaSimplificada(55L)).thenReturn(listOf(simplificada))

        val result = finderController.getDespesaSimplificada(55L)

        assertNotNull(result)
        assertEquals(1, result.size)
    }

    @Test
    fun shouldReturnTotalValueSpentByUnidadeFederativaId() {
        `when`(finderService.getTotalValueSpentByUnidadeFederativaId(44L)).thenReturn(1234.56)

        val result = finderController.getTotalValueSpentByUnidadeFederativaId(44L)

        assertEquals(1234.56, result)
    }
}