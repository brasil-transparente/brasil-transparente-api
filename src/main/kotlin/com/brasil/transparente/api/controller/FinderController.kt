package com.brasil.transparente.api.controller

import com.brasil.transparente.api.dto.DisplayableElementDTO
import com.brasil.transparente.api.entity.DespesaSimplificada
import com.brasil.transparente.api.service.FinderService
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@CrossOrigin(origins = ["*"], methods = [RequestMethod.GET])
@OpenAPIDefinition(
    info = Info(
        title = "Brasil Transparente REST API",
        version = "1.0.0-2024",
        description = "API para exploração de gastos públicos brasileiros efetivamente pagos em nível federal. " +
                "Esta API expõe duas perspectivas de dados estritamente separadas: endpoints de Resumo para análise agregada de alto nível e endpoints de Exploração para navegação hierárquica detalhada. " +
                "Todos os identificadores devem ser obtidos dinamicamente a partir das respostas da API e não devem ser inferidos, adivinhados ou fixados no código. "
    )
)
@RestController
class FinderController(private val finderService: FinderService) {

    @Tag(
        name = "Exploration",
        description = "Endpoints de navegação hierárquica que devem ser usados estritamente na ordem documentada. IDs obtidos em um nível são válidos somente para o nível imediatamente seguinte."
    )
    @Operation(
        summary = "Ponto de entrada – Lista os Poderes de uma Unidade Federativa",
        description = "Ponto de entrada obrigatório para exploração hierárquica. Retorna os poderes Executivo, Legislativo, Judiciário e outros. " +
                "Para análise do Governo Federal brasileiro, unidadeFederativaId deve ser 1. Toda exploração hierárquica deve começar por este endpoint."
    )
    @GetMapping(value = ["/unidade-federativa/{unidadeFederativaId}/poderes"], produces = [APPLICATION_JSON_VALUE])
    fun getPoderesByUnidadeFederativa(
        @Parameter(description = "Identificador da Unidade Federativa. Use 1 para o Governo Federal brasileiro. Este valor é o ponto de partida obrigatório para a exploração.", required = true)
        @PathVariable unidadeFederativaId: Long
    ): List<DisplayableElementDTO> = finderService.getPoderesByUnidadeFederativa(unidadeFederativaId)

    @Tag(name = "Exploration")
    @Operation(
        summary = "Lista Ministérios por Poder",
        description = "Retorna ministérios ou tribunais associados a um Poder específico. Este endpoint deve ser chamado apenas após obter um ID de Poder válido em /unidade-federativa/{id}/poderes. IDs de qualquer outro nível hierárquico são inválidos."
    )
    @GetMapping(value = ["/poder/{idPoder}/ministerios"], produces = [APPLICATION_JSON_VALUE])
    fun getMinisterioByPoderId(
        @Parameter(description = "Identificador do Poder obtido exclusivamente do endpoint de poderes.", required = true)
        @PathVariable idPoder: Long
    ): List<DisplayableElementDTO> = finderService.getMinisterioByPoderId(idPoder)

    @Tag(name = "Exploration")
    @Operation(
        summary = "Lista Órgãos por Ministério",
        description = "Retorna órgãos associados a um Ministério específico. Este endpoint deve ser chamado apenas após obter um ID de Ministério em /poder/{idPoder}/ministerios. Pular níveis da hierarquia ou reutilizar IDs de outros contextos não é permitido."
    )
    @GetMapping(value = ["/ministerio/{idMinisterio}/orgaos"], produces = [APPLICATION_JSON_VALUE])
    fun getOrgaoByMinisterioId(
        @Parameter(description = "Identificador do Ministério obtido exclusivamente do endpoint de ministérios.", required = true)
        @PathVariable idMinisterio: Long
    ): List<DisplayableElementDTO> = finderService.getOrgaoByMinisterioId(idMinisterio)

    @Tag(name = "Exploration")
    @Operation(
        summary = "Lista Unidades Gestoras por Órgão",
        description = "Retorna Unidades Gestoras responsáveis pela execução orçamentária dentro de um Órgão específico. Este endpoint deve ser chamado apenas após obter um ID de Órgão em /ministerio/{idMinisterio}/orgaos."
    )
    @GetMapping(value = ["/orgao/{idOrgao}/unidades-gestoras"], produces = [APPLICATION_JSON_VALUE])
    fun getUnidadeGestoraByOrgaoId(
        @Parameter(description = "Identificador do Órgão obtido exclusivamente do endpoint de órgãos.", required = true)
        @PathVariable idOrgao: Long
    ): List<DisplayableElementDTO> = finderService.getUnidadeGestoraByOrgaoId(idOrgao)

    @Tag(name = "Exploration")
    @Operation(
        summary = "Nível final – Lista Elementos de Despesa por Unidade Gestora",
        description = "Nível final da exploração hierárquica. Retorna elementos de despesa detalhados e efetivamente pagos por uma Unidade Gestora. " +
                "Este endpoint deve ser chamado apenas após obter um ID de Unidade Gestora em /orgao/{idOrgao}/unidades-gestoras. " +
                "Não é possível aprofundar a exploração além deste nível."
    )
    @GetMapping(value = ["/unidade-gestora/{idUnidadeGestora}/elemento-despesa"], produces = [APPLICATION_JSON_VALUE])
    fun getElementoDespesaByUnidadeGestoraId(
        @Parameter(description = "Identificador da Unidade Gestora obtido exclusivamente do endpoint de unidades gestoras.", required = true)
        @PathVariable idUnidadeGestora: Long
    ): List<DisplayableElementDTO> = finderService.getElementoDespesaByUnidadeGestoraId(idUnidadeGestora)

    @Tag(
        name = "Summary",
        description = "Visões agregadas de alto nível para análise macro. Dados de Resumo não devem ser combinados ou reagregados com endpoints de Exploração."
    )
    @Operation(
        summary = "Relatório Simplificado de Gastos por Unidade Federativa",
        description = "Agregação reclassificada de alto nível das categorias de gasto público, com base na metodologia do projeto. " +
                "Este endpoint é destinado somente a análises macro e explicações narrativas. " +
                "Resultados deste endpoint não devem ser combinados ou agregados com endpoints de Exploração."
    )
    @GetMapping(value = ["/despesa-simplificada/{unidadeFederativaId}"], produces = [APPLICATION_JSON_VALUE])
    fun getDespesaSimplificada(
        @Parameter(description = "Identificador da Unidade Federativa. Use 1 para o Governo Federal brasileiro.", required = true)
        @PathVariable unidadeFederativaId: Long
    ): List<DespesaSimplificada> = finderService.getDespesaSimplificada(unidadeFederativaId)

    @Tag(name = "Summary")
    @Operation(
        summary = "Total de Gastos Pagos por Unidade Federativa",
        description = "Valor total de gastos públicos efetivamente pagos por uma unidade federativa. Este valor representa um retrato anual em regime de caixa e não deve ser decomposto usando endpoints de Exploração."
    )
    @GetMapping(value = ["/unidade-federativa/{unidadeFederativaId}/total-value-spent"], produces = [APPLICATION_JSON_VALUE])
    fun getTotalValueSpentByUnidadeFederativaId(
        @Parameter(description = "Identificador da Unidade Federativa. Use 1 para o Governo Federal brasileiro.", required = true)
        @PathVariable unidadeFederativaId: Long
    ): Double = finderService.getTotalValueSpentByUnidadeFederativaId(unidadeFederativaId)
}