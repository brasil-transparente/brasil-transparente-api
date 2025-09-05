package com.brasil.transparente.api.controller;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.dto.PaginatedResponse;
import com.brasil.transparente.api.entity.*;
import com.brasil.transparente.api.service.FinderService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.*;

@CrossOrigin(origins = {
        "https://brasil-transparente.web.app",
        "https://brasil-transparente.firebaseapp.com/",
        "https://brasiltransparente.digital"
})
@OpenAPIDefinition(info = @Info(title = "API REST Brasil Transparente", version = "1.0.0"))
@Tag(name = "Busca")
@RestController
public class FinderController {

    private final FinderService finderService;

    public FinderController(FinderService finderService) {
        this.finderService = finderService;
    }

    @GetMapping(value = "/unidade-federativa/{unidadeFederativaId}/poderes", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getPoderesByUnidadeFederativa(@PathVariable("unidadeFederativaId") Long unidadeFederativaId) {
        return finderService.getPodereByUnidadeFederativa(unidadeFederativaId);
    }

    @GetMapping(value = "/unidade-federativa/{unidadeFederativaId}/renuncias-fiscais", produces = APPLICATION_JSON_VALUE)
    public PaginatedResponse<RenunciaFiscal> getRenunciasFiscaisByUnidadeFederativa(@PathVariable("unidadeFederativaId") Long unidadeFederativaId,
                                                                                    @RequestParam(defaultValue = "0") int page,
                                                                                    @RequestParam(defaultValue = "30") int size){
        return finderService.getRenunciaFiscalByUnidadeFederativaId(unidadeFederativaId, page, size);
    }

    @GetMapping(value = "/unidade-federativa/{unidadeFederativaId}/renuncias-fiscais/total", produces = APPLICATION_JSON_VALUE)
    public ValorTotal getTotaisRenunciasFiscaisByUnidadeFederativa(@PathVariable("unidadeFederativaId") Long unidadeFederativaId){
        return finderService.getRenunciaFiscalTotalByUnidadeFederativaId(unidadeFederativaId);
    }

    @GetMapping(value = "/poder/{idPoder}/ministerios", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getMinisterioByPoderId(@PathVariable("idPoder") Long poderId) {
        return finderService.getMinisterioByPoderId(poderId);
    }

    @GetMapping(value = "/ministerio/{idMinisterio}/orgaos", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getOrgaoByMinisterioId(@PathVariable("idMinisterio") Long ministerioId) {
        return finderService.getOrgaoByMinisterioId(ministerioId);
    }

    @GetMapping(value = "/orgao/{idOrgao}/unidades-gestoras", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getUnidadeGestoraByOrgaoId(@PathVariable("idOrgao") Long orgaoId) {
        return finderService.getUnidadeGestoraByOrgaoId(orgaoId);
    }

    @GetMapping(value = "/unidade-gestora/{idUnidadeGestora}/elemento-despesa", produces = APPLICATION_JSON_VALUE)
    public List<DisplayableElementDTO> getElementoDespesaByUnidadeGestoraId(@PathVariable("idUnidadeGestora") Long unidadeGestoraId) {
        return finderService.getElementoDespesaByUnidadeGestoraId(unidadeGestoraId);
    }

    @GetMapping(value = "/despesa-simplificada/{unidadeFederativaId}", produces = APPLICATION_JSON_VALUE)
    public List<DespesaSimplificada> getSimplifiedReport(@PathVariable("unidadeFederativaId") Long unidadeFederativaId) {
        return finderService.getSimplifiedReport(unidadeFederativaId);
    }

    @GetMapping(value = "/unidade-federativa/{unidadeFederativaId}/total-value-spent", produces = APPLICATION_JSON_VALUE)
    public Double getTotalValueSpentByUnidadeFederativaId(@PathVariable("unidadeFederativaId") Long unidadeFederativaId) {
        return finderService.getTotalValueSpentByUnidadeFederativaId(unidadeFederativaId);
    }

}
