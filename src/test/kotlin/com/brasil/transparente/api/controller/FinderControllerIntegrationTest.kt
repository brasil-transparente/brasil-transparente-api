package com.brasil.transparente.api.controller

import com.brasil.transparente.api.dto.DisplayableElementDTO
import com.brasil.transparente.api.entity.DespesaSimplificada
import com.brasil.transparente.api.service.FinderService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
class FinderControllerIntegrationTest {

    @Mock
    private lateinit var finderService: FinderService

    private lateinit var mockMvc: MockMvc

    private val sampleElement = DisplayableElementDTO(
        id = 1L,
        name = "Elemento Teste",
        totalValueSpent = 1500.0,
        percentageOfTotal = 15.0,
        level = 0
    )

    @BeforeEach
    fun setUp() {
        val controller = FinderController(finderService)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun shouldGetPoderes() {
        `when`(finderService.getPoderes()).thenReturn(listOf(sampleElement))

        mockMvc.perform(get("/poderes").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("Elemento Teste"))
            .andExpect(jsonPath("$[0].totalValueSpent").value(1500.0))
            .andExpect(jsonPath("$[0].percentageOfTotal").value(15.0))
            .andExpect(jsonPath("$[0].level").value(0))
    }

    @Test
    fun shouldGetMinisteriosByPoderId() {
        `when`(finderService.getMinisterioByPoderId(1L)).thenReturn(listOf(sampleElement.copy(level = 1)))

        mockMvc.perform(get("/poder/1/ministerios").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].level").value(1))
    }

    @Test
    fun shouldGetOrgaosByMinisterioId() {
        `when`(finderService.getOrgaoByMinisterioId(2L)).thenReturn(listOf(sampleElement.copy(level = 2)))

        mockMvc.perform(get("/ministerio/2/orgaos").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].level").value(2))
    }

    @Test
    fun shouldGetUnidadesGestorasByOrgaoId() {
        `when`(finderService.getUnidadeGestoraByOrgaoId(3L)).thenReturn(listOf(sampleElement.copy(level = 3)))

        mockMvc.perform(get("/orgao/3/unidades-gestoras").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].level").value(3))
    }

    @Test
    fun shouldGetElementoDespesaByUnidadeGestoraId() {
        `when`(finderService.getElementoDespesaByUnidadeGestoraId(4L)).thenReturn(listOf(sampleElement.copy(level = 4)))

        mockMvc.perform(get("/unidade-gestora/4/elemento-despesa").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].level").value(4))
    }

    @Test
    fun shouldGetDespesaSimplificada() {
        val despesa = DespesaSimplificada().apply {
            despesaSimplificadaId = 10L
            name = "Previdência"
            totalValue = 50000.0
            percentageOfTotal = 40.0
        }
        `when`(finderService.getDespesaSimplificada()).thenReturn(listOf(despesa))

        mockMvc.perform(get("/despesa-simplificada").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].despesaSimplificadaId").value(10))
            .andExpect(jsonPath("$[0].name").value("Previdência"))
            .andExpect(jsonPath("$[0].totalValue").value(50000.0))
            .andExpect(jsonPath("$[0].percentageOfTotal").value(40.0))
    }

    @Test
    fun shouldGetTotalValueSpent() {
        `when`(finderService.getTotalValueSpent()).thenReturn(99999.99)

        mockMvc.perform(get("/total-value-spent").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().string("99999.99"))
    }
}
