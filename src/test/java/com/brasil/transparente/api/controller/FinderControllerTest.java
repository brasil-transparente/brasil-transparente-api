package com.brasil.transparente.api.controller;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.entity.DespesaSimplificada;
import com.brasil.transparente.api.service.FinderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FinderControllerTest {

    @Mock
    private FinderService finderService;

    @InjectMocks
    private FinderController finderController;

    private final DisplayableElementDTO displayableElementDTO = DisplayableElementDTO.builder()
            .id(1L)
            .name("Test Name")
            .totalValueSpent(100.0)
            .percentageOfTotal(5.0)
            .level(1)
            .build();


    @Test
    void shouldReturnUnidadeGestoraList() {
        when(finderService.getUnidadeGestoraByOrgaoId(123L)).thenReturn(List.of(displayableElementDTO));

        var result = finderController.getUnidadeGestoraByOrgaoId(123L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Name", result.get(0).getName());
    }

    @Test
    void shouldReturnPoderesByUnidadeFederativa() {
        when(finderService.getPodereByUnidadeFederativa(99L)).thenReturn(List.of(displayableElementDTO));

        var result = finderController.getPoderesByUnidadeFederativa(99L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Name", result.getFirst().getName());
    }

    @Test
    void shouldReturnMinisteriosByPoderId() {
        when(finderService.getMinisterioByPoderId(88L)).thenReturn(List.of(displayableElementDTO));

        var result = finderController.getMinisterioByPoderId(88L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Name", result.getFirst().getName());
    }

    @Test
    void shouldReturnOrgaosByMinisterioId() {
        when(finderService.getOrgaoByMinisterioId(77L)).thenReturn(List.of(displayableElementDTO));

        var result = finderController.getOrgaoByMinisterioId(77L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Name", result.getFirst().getName());
    }

    @Test
    void shouldReturnElementoDespesaByUnidadeGestoraId() {
        when(finderService.getElementoDespesaByUnidadeGestoraId(66L)).thenReturn(List.of(displayableElementDTO));

        var result = finderController.getElementoDespesaByUnidadeGestoraId(66L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Name", result.getFirst().getName());
    }

    @Test
    void shouldReturnDespesaSimplificadaByUnidadeFederativaId() {
        DespesaSimplificada simplificada = DespesaSimplificada.builder().build();

        when(finderService.getSimplifiedReport(55L)).thenReturn(List.of(simplificada));

        var result = finderController.getSimplifiedReport(55L);

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnTotalValueSpentByUnidadeFederativaId() {
        when(finderService.getTotalValueSpentByUnidadeFederativaId(44L)).thenReturn(1234.56);

        var result = finderController.getTotalValueSpentByUnidadeFederativaId(44L);

        assertEquals(1234.56, result);
    }

}
