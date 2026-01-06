package com.brasil.transparente.api.controller;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.entity.DespesaSimplificada;
import com.brasil.transparente.api.service.FinderService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@OpenAPIDefinition(info = @Info(
        title = "API REST Brasil Transparente",
        version = "1.0.0",
        description = "API para exploração de gastos públicos reais (pagos) da União e Estados. " +
                "A navegação é hierárquica: UF -> Poder -> Ministério -> Órgão -> Unidade Gestora -> Despesa."
))
@Tag(name = "Busca", description = "Endpoints para exploração hierárquica e relatórios de despesas")
@RestController
public class FinderController {

    private final FinderService finderService;

    public FinderController(FinderService finderService) {
        this.finderService = finderService;
    }

    @Operation(
            summary = "Listar Poderes de uma UF",
            description = "Ponto de entrada para os dados. Retorna Executivo, Judiciário, Legislativo, etc. O ID 1 refere-se à União (Governo Federal)."
    )
    @GetMapping(value = "/unidade-federativa/{unidadeFederativaId}/poderes", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getPoderesByUnidadeFederativa(
            @Parameter(description = "ID da Unidade Federativa (Ex: 1 para União)")
            @PathVariable("unidadeFederativaId") Long unidadeFederativaId) {
        return finderService.getPodereByUnidadeFederativa(unidadeFederativaId);
    }

    @Operation(
            summary = "Listar Ministérios/Pastas por Poder",
            description = "Retorna os ministérios (no Executivo) ou tribunais (no Judiciário) vinculados ao ID do poder informado."
    )
    @GetMapping(value = "/poder/{idPoder}/ministerios", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getMinisterioByPoderId(
            @Parameter(description = "ID do Poder obtido no endpoint anterior")
            @PathVariable("idPoder") Long poderId) {
        return finderService.getMinisterioByPoderId(poderId);
    }

    @Operation(summary = "Listar Órgãos por Ministério", description = "Retorna as entidades vinculadas a um ministério ou pasta específica.")
    @GetMapping(value = "/ministerio/{idMinisterio}/orgaos", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getOrgaoByMinisterioId(@PathVariable("idMinisterio") Long ministerioId) {
        return finderService.getOrgaoByMinisterioId(ministerioId);
    }

    @Operation(summary = "Listar Unidades Gestoras por Órgão", description = "Retorna as unidades gestoras responsáveis pela execução do gasto.")
    @GetMapping(value = "/orgao/{idOrgao}/unidades-gestoras", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getUnidadeGestoraByOrgaoId(@PathVariable("idOrgao") Long orgaoId) {
        return finderService.getUnidadeGestoraByOrgaoId(orgaoId);
    }

    @Operation(
            summary = "Listar Elementos de Despesa",
            description = "O nível final da hierarquia. Retorna a lista detalhada de despesas pagas pela Unidade Gestora."
    )
    @GetMapping(value = "/unidade-gestora/{idUnidadeGestora}/elemento-despesa", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getElementoDespesaByUnidadeGestoraId(@PathVariable("idUnidadeGestora") Long unidadeGestoraId) {
        return finderService.getElementoDespesaByUnidadeGestoraId(unidadeGestoraId);
    }

    @Operation(
            summary = "Relatório Simplificado por UF",
            description = "Retorna um resumo agrupado das maiores despesas da Unidade Federativa (Saúde, Educação, Previdência, etc.), reclassificadas conforme a metodologia do projeto."
    )
    @GetMapping(value = "/despesa-simplificada/{unidadeFederativaId}", produces = APPLICATION_JSON_VALUE)
    public List<DespesaSimplificada> getSimplifiedReport(@PathVariable("unidadeFederativaId") Long unidadeFederativaId) {
        return finderService.getSimplifiedReport(unidadeFederativaId);
    }

    @Operation(summary = "Gasto Total da UF", description = "Retorna o valor somado de todos os gastos efetivamente pagos pela Unidade Federativa no ano.")
    @GetMapping(value = "/unidade-federativa/{unidadeFederativaId}/total-value-spent", produces = APPLICATION_JSON_VALUE)
    public Double getTotalValueSpentByUnidadeFederativaId(@PathVariable("unidadeFederativaId") Long unidadeFederativaId) {
        return finderService.getTotalValueSpentByUnidadeFederativaId(unidadeFederativaId);
    }
}