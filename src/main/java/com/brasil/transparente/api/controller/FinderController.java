package com.brasil.transparente.api.controller;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.entity.*;
import com.brasil.transparente.api.service.FinderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FinderController {


    private FinderService finderService;
    public FinderController( FinderService finderService)
    {
        this.finderService = finderService;
    }

    @GetMapping("/poderes")
    public List<DisplayableElementDTO> getPoderList() {
        return finderService.getPoderList();
    }

    @GetMapping("/poder/{idPoder}/ministerios")
    public List<DisplayableElementDTO> getMinisterioByPoderId(@PathVariable("idPoder") Long poderId) {
        return finderService.getMinisterioByPoderId(poderId);
    }

    @GetMapping("/ministerio/{idMinisterio}/orgaos")
    public List<DisplayableElementDTO> getOrgaoByMinisterioId(@PathVariable("idMinisterio") Long ministerioId) {
        return finderService.getOrgaoByMinisterioId(ministerioId);
    }

    @GetMapping("/orgao/{idOrgao}/unidades-gestoras")
    public List<DisplayableElementDTO> getUnidadeGestoraByOrgaoId(@PathVariable("idOrgao") Long orgaoId) {
        return finderService.getUnidadeGestoraByOrgaoId(orgaoId);
    }

    @GetMapping("/unidade-gestora/{idUnidadeGestora}/elemento-despesa")
    public List<DisplayableElementDTO> getElementoDespesaByUnidadeGestoraId(@PathVariable("idUnidadeGestora") Long unidadeGestoraId) {
        return finderService.getElementoDespesaByUnidadeGestoraId(unidadeGestoraId);
    }

    @GetMapping("/despesa-simplificada")
    public List<DespesaSimplicada> getSimplifiedReport() {
        return finderService.getSimplifiedReport();
    }

    @GetMapping("/gasto-total")
    public Double getGastoTotal() {
        return finderService.getGastoTotal();
    }

}
