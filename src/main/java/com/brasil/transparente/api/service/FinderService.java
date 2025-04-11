package com.brasil.transparente.api.service;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.entity.*;
import com.brasil.transparente.api.repository.*;
import com.brasil.transparente.api.util.OrdererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
@Service
public class FinderService {

    @Autowired
    private PoderRepository poderRepository;
    @Autowired
    private MinisterioRepository ministerioRepository;
    @Autowired
    private OrgaoRepository orgaoRepository;
    @Autowired
    private UnidadeGestoraRepository unidadeGestoraRepository;
    @Autowired
    private ElementoDespesaRepository elementoDespesaRepository;
    @Autowired
    private GastoTotalRepository gastoTotalRepository;
    @Autowired
    private DespesaSimplificadaRepository despesaSimplificadaRepository;
    @Autowired
    private OrdererService ordererService;

    private static final int PODER_LEVEL = 0;
    private static final int MINISTERIO_LEVEL = 1;
    private static final int ORGAO_LEVEL = 2;
    private static final int UNIDADE_GESTORA_LEVEL = 3;
    private static final int ELEMENTO_DESPESA_LEVEL = 4;

    public List<DisplayableElementDTO> getPoderList() {
        List<Poder> poderList = poderRepository.findAll();
        List<DisplayableElementDTO> displayableElementDTOList = new ArrayList<>();
        for (Poder poder : poderList) {
            displayableElementDTOList.add(mapToDisplayableElementDto(poder.getPoderId(), poder.getNamePoder(),
                    poder.getTotalValueSpent(), poder.getPercentageOfTotal(), PODER_LEVEL));
        }
        return displayableElementDTOList;
    }

    public List<DisplayableElementDTO> getMinisterioByPoderId(Long poderId) {
        List<Ministerio> ministerioList = ministerioRepository.findByPoderPoderId(poderId);
        List<DisplayableElementDTO> displayableElementDTOList = new ArrayList<>();
        for (Ministerio ministerio : ministerioList) {
            displayableElementDTOList.add(mapToDisplayableElementDto(ministerio.getMinisterioId(), ministerio.getNameMinisterio(),
                    ministerio.getTotalValueSpent(), ministerio.getPercentageOfTotal(), MINISTERIO_LEVEL));
        }

        return displayableElementDTOList;
    }

    public List<DisplayableElementDTO> getOrgaoByMinisterioId(Long ministerioId) {
        List<Orgao> orgaoList = orgaoRepository.findByMinisterioMinisterioId(ministerioId);
        List<DisplayableElementDTO> displayableElementDTOList = new ArrayList<>();
        for (Orgao orgao : orgaoList) {
            displayableElementDTOList.add(mapToDisplayableElementDto(orgao.getOrgaoId(), orgao.getNameOrgao(),
                    orgao.getTotalValueSpent(), orgao.getPercentageOfTotal(), ORGAO_LEVEL));
        }

        return displayableElementDTOList;
    }

    public List<DisplayableElementDTO> getUnidadeGestoraByOrgaoId(Long orgaoId) {
        List<UnidadeGestora> unidadeGestoraList = unidadeGestoraRepository.findByOrgaoOrgaoId(orgaoId);
        List<DisplayableElementDTO> displayableElementDTOList = new ArrayList<>();
        for (UnidadeGestora unidadeGestora : unidadeGestoraList) {
            displayableElementDTOList.add(mapToDisplayableElementDto(unidadeGestora.getUnidadeGestoraId(), unidadeGestora.getNameUnidadeGestora(),
                    unidadeGestora.getTotalValueSpent(), unidadeGestora.getPercentageOfTotal(), UNIDADE_GESTORA_LEVEL));
        }
        return displayableElementDTOList;
    }

    public List<DisplayableElementDTO> getElementoDespesaByUnidadeGestoraId(Long unidadeGestoraId) {
        List<ElementoDespesa> elementoDespesaList = elementoDespesaRepository.findByUnidadeGestoraUnidadeGestoraId(unidadeGestoraId);
        List<DisplayableElementDTO> displayableElementDTOList = new ArrayList<>();
        for (ElementoDespesa elementoDespesa : elementoDespesaList) {
            displayableElementDTOList.add(mapToDisplayableElementDto(elementoDespesa.getElementoDespesaId(), elementoDespesa.getNameElementoDespesa(),
                    elementoDespesa.getTotalValueSpent(), elementoDespesa.getPercentageOfTotal(), ELEMENTO_DESPESA_LEVEL));
        }
        return displayableElementDTOList;
    }

    public List<DespesaSimplicada> getSimplifiedReport() {
        List<DespesaSimplicada> despesaSimplicadaList = despesaSimplificadaRepository.findAll();
        ordererService.orderDespesaSimplificadaListBySpending(despesaSimplicadaList);
        return despesaSimplicadaList;
    }

    public Double getGastoTotal() {
        return gastoTotalRepository.findAll().getFirst().getGastoTotalValue();
    }

    private DisplayableElementDTO mapToDisplayableElementDto(Long id, String name, double totalValueSpent, double percentageOfTotal, int levelOfElement) {
        return DisplayableElementDTO.builder()
                .id(id)
                .name(name)
                .totalValueSpent(totalValueSpent)
                .percentageOfTotal(percentageOfTotal)
                .level(levelOfElement)
                .build();
    }

}
