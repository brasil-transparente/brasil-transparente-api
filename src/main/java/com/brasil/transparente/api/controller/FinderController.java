package com.brasil.transparente.api.controller;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.entity.DespesaSimplificada;
import com.brasil.transparente.api.service.FinderService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
@OpenAPIDefinition(
        info = @Info(
                title = "Brasil Transparente REST API",
                version = "1.0.0-2024",
                description = "Canonical API for exploration of effectively paid Brazilian public spending at federal and state levels. "
                        + "This API exposes two strictly separated data perspectives: Summary endpoints for high-level aggregated analysis, "
                        + "and Exploration endpoints for hierarchical drill-down. "
                        + "All identifiers MUST be obtained dynamically from API responses and MUST NOT be inferred, guessed, or hard-coded. "
                        + "The OpenAPI specification is the single source of truth for structure, hierarchy, and behavior."
        )
)
@RestController
@AllArgsConstructor
public class FinderController {

    private final FinderService finderService;

    @Tag(
            name = "Exploration",
            description = "Hierarchical drill-down endpoints that MUST be used strictly in the documented order. IDs obtained at one level are valid ONLY for the immediately next level."
    )
    @Operation(
            summary = "ENTRY POINT – List Powers of a Federative Unit",
            description = "MANDATORY ENTRY POINT for hierarchical exploration. Returns Executive, Legislative, Judicial, and other powers. "
                    + "For Brazilian Federal Government analysis, unidadeFederativaId MUST be 1. All hierarchical exploration MUST start from this endpoint."
    )
    @GetMapping(value = "/unidade-federativa/{unidadeFederativaId}/poderes", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getPoderesByUnidadeFederativa(
            @Parameter(description = "Federative Unit identifier. Use 1 for the Brazilian Federal Government. This value is the mandatory starting point for exploration.", required = true)
            @PathVariable Long unidadeFederativaId
    ) {
        return finderService.getPoderesByUnidadeFederativa(unidadeFederativaId);
    }

    @Tag(name = "Exploration")
    @Operation(
            summary = "List Ministries by Power",
            description = "Returns ministries or courts associated with a specific Power. This endpoint MUST only be called after obtaining a valid Power ID from /unidade-federativa/{id}/poderes. IDs from any other hierarchy level are INVALID."
    )
    @GetMapping(value = "/poder/{idPoder}/ministerios", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getMinisterioByPoderId(
            @Parameter(description = "Power identifier obtained EXCLUSIVELY from the powers endpoint.", required = true)
            @PathVariable Long idPoder
    ) {
        return finderService.getMinisterioByPoderId(idPoder);
    }

    @Tag(name = "Exploration")
    @Operation(
            summary = "List Agencies by Ministry",
            description = "Returns agencies associated with a specific Ministry. This endpoint MUST only be called after obtaining a Ministry ID from /poder/{idPoder}/ministerios. Skipping hierarchy levels or reusing IDs from other contexts is NOT allowed."
    )
    @GetMapping(value = "/ministerio/{idMinisterio}/orgaos", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getOrgaoByMinisterioId(
            @Parameter(description = "Ministry identifier obtained EXCLUSIVELY from the ministries endpoint.", required = true)
            @PathVariable Long idMinisterio
    ) {
        return finderService.getOrgaoByMinisterioId(idMinisterio);
    }

    @Tag(name = "Exploration")
    @Operation(
            summary = "List Management Units by Agency",
            description = "Returns Management Units responsible for budget execution within a specific Agency. This endpoint MUST only be called after obtaining an Agency ID from /ministerio/{idMinisterio}/orgaos."
    )
    @GetMapping(value = "/orgao/{idOrgao}/unidades-gestoras", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getUnidadeGestoraByOrgaoId(
            @Parameter(description = "Agency identifier obtained EXCLUSIVELY from the agencies endpoint.", required = true)
            @PathVariable Long idOrgao
    ) {
        return finderService.getUnidadeGestoraByOrgaoId(idOrgao);
    }

    @Tag(name = "Exploration")
    @Operation(
            summary = "FINAL LEVEL – List Expense Elements by Management Unit",
            description = "Final level of the hierarchical exploration. Returns detailed paid expense elements for a Management Unit. "
                    + "This endpoint MUST only be called after obtaining a Management Unit ID from /orgao/{idOrgao}/unidades-gestoras. "
                    + "No further drill-down is possible beyond this level."
    )
    @GetMapping(value = "/unidade-gestora/{idUnidadeGestora}/elemento-despesa", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getElementoDespesaByUnidadeGestoraId(
            @Parameter(description = "Management Unit identifier obtained EXCLUSIVELY from the management units endpoint.", required = true)
            @PathVariable Long idUnidadeGestora
    ) {
        return finderService.getElementoDespesaByUnidadeGestoraId(idUnidadeGestora);
    }

    @Tag(
            name = "Summary",
            description = "High-level aggregated views for macro analysis. Summary data MUST NOT be combined or re-aggregated with Exploration endpoints."
    )
    @Operation(
            summary = "Simplified Spending Report by Federative Unit",
            description = "High-level reclassified aggregation of public spending categories based on project methodology. "
                    + "This endpoint is intended ONLY for macro analysis and narrative explanations. "
                    + "Results from this endpoint MUST NOT be merged or aggregated with Exploration endpoints."
    )
    @GetMapping(value = "/despesa-simplificada/{unidadeFederativaId}", produces = APPLICATION_JSON_VALUE)
    public List<DespesaSimplificada> getSimplifiedReport(
            @Parameter(description = "Federative Unit identifier. Use 1 for the Brazilian Federal Government.", required = true)
            @PathVariable Long unidadeFederativaId
    ) {
        return finderService.getSimplifiedReport(unidadeFederativaId);
    }

    @Tag(name = "Summary")
    @Operation(
            summary = "Total Paid Spending by Federative Unit",
            description = "Total amount of effectively paid public spending for a federative unit. This value represents a cash-based annual snapshot and MUST NOT be decomposed using Exploration endpoints."
    )
    @GetMapping(value = "/unidade-federativa/{unidadeFederativaId}/total-value-spent", produces = APPLICATION_JSON_VALUE)
    public Double getTotalValueSpentByUnidadeFederativaId(
            @Parameter(description = "Federative Unit identifier. Use 1 for the Brazilian Federal Government.", required = true)
            @PathVariable Long unidadeFederativaId
    ) {
        return finderService.getTotalValueSpentByUnidadeFederativaId(unidadeFederativaId);
    }
}
