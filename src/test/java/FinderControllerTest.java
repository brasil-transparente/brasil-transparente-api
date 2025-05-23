import com.brasil.transparente.api.BrasilTransparenteApiApplication;
import com.brasil.transparente.api.controller.FinderController;
import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.entity.DespesaSimplificada;
import com.brasil.transparente.api.service.FinderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FinderController.class)
@ContextConfiguration(classes = BrasilTransparenteApiApplication.class)
public class FinderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FinderService finderService;

    private final DisplayableElementDTO displayableElementDTO = DisplayableElementDTO.builder()
            .id(1L)
            .name("Test Name")
            .totalValueSpent(100.0)
            .percentageOfTotal(5.0)
            .level(1)
            .build();

    @Test
    void shouldReturnUnidadeGestoraListAsJson() throws Exception {
        when(finderService.getUnidadeGestoraByOrgaoId(123L)).thenReturn(List.of(displayableElementDTO));

        mockMvc.perform(get("/orgao/123/unidades-gestoras"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Name"));
    }

    @Test
    void shouldReturnPoderesByUnidadeFederativaAsJson() throws Exception {
        when(finderService.getPodereByUnidadeFederativa(99L)).thenReturn(List.of(displayableElementDTO));

        mockMvc.perform(get("/unidade-federativa/99/poderes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Name"));
    }

    @Test
    void shouldReturnMinisteriosByPoderIdAsJson() throws Exception {
        when(finderService.getMinisterioByPoderId(88L)).thenReturn(List.of(displayableElementDTO));

        mockMvc.perform(get("/poder/88/ministerios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Name"));
    }

    @Test
    void shouldReturnOrgaosByMinisterioIdAsJson() throws Exception {
        when(finderService.getOrgaoByMinisterioId(77L)).thenReturn(List.of(displayableElementDTO));

        mockMvc.perform(get("/ministerio/77/orgaos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Name"));
    }

    @Test
    void shouldReturnElementoDespesaByUnidadeGestoraIdAsJson() throws Exception {
        when(finderService.getElementoDespesaByUnidadeGestoraId(66L)).thenReturn(List.of(displayableElementDTO));

        mockMvc.perform(get("/unidade-gestora/66/elemento-despesa"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Test Name"));
    }

    @Test
    void shouldReturnDespesaSimplificadaByUnidadeFederativaId() throws Exception {
        DespesaSimplificada simplificada = DespesaSimplificada.builder().build();

        when(finderService.getSimplifiedReport(55L)).thenReturn(List.of(simplificada));

        mockMvc.perform(get("/despesa-simplificada/55"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReturnTotalValueSpentByUnidadeFederativaId() throws Exception {
        when(finderService.getTotalValueSpentByUnidadeFederativaId(44L)).thenReturn(1234.56);

        mockMvc.perform(get("/unidade-federativa/44/total-value-spent"))
                .andExpect(status().isOk())
                .andExpect(content().string("1234.56"));
    }

}
