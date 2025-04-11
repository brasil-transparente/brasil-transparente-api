package com.brasil.transparente.api.controller;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.entity.*;
import com.brasil.transparente.api.service.FinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FinderController {

    @Autowired
    private FinderService finderService;

    @GetMapping("/getPoderes")
    public List<DisplayableElementDTO> getPoderList() {
        return finderService.getPoderList();
    }

    @GetMapping("/getMinisterioByPoderId")
    public List<DisplayableElementDTO> getMinisterioByPoderId(@RequestParam Long poderId) {
        return finderService.getMinisterioByPoderId(poderId);
    }

    @GetMapping("/getOrgaoByMinisterioId")
    public List<DisplayableElementDTO> getOrgaoByMinisterioId(@RequestParam Long ministerioId) {
        return finderService.getOrgaoByMinisterioId(ministerioId);
    }

    @GetMapping("/getUnidadeGestoraByOrgaoId")
    public List<DisplayableElementDTO> getUnidadeGestoraByOrgaoId(@RequestParam Long orgaoId) {
        return finderService.getUnidadeGestoraByOrgaoId(orgaoId);
    }

    @GetMapping("/getElementoDespesaByUnidadeGestoraId")
    public List<DisplayableElementDTO> getElementoDespesaByUnidadeGestoraId(@RequestParam Long unidadeGestoraId) {
        return finderService.getElementoDespesaByUnidadeGestoraId(unidadeGestoraId);
    }

    @GetMapping("/getSimplifiedReport")
    public List<DespesaSimplicada> getSimplifiedReport() {
        return finderService.getSimplifiedReport();
    }

    @GetMapping("/getGastoTotal")
    public Double getGastoTotal() {
        return finderService.getGastoTotal();
    }

}
