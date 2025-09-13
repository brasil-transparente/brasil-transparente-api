package com.brasil.transparente.api.service;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.dto.PaginatedResponse;
import com.brasil.transparente.api.entity.*;
import com.brasil.transparente.api.repository.*;
import com.brasil.transparente.api.util.MapperService;
import com.brasil.transparente.api.util.OrdererService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class FinderService {

    private final PoderRepository poderRepository;
    private final MinisterioRepository ministerioRepository;
    private final OrgaoRepository orgaoRepository;
    private final UnidadeGestoraRepository unidadeGestoraRepository;
    private final ElementoDespesaRepository elementoDespesaRepository;
    private final UnidadeFederativaRepository unidadeFederativaRepository;
    private final DespesaSimplificadaRepository despesaSimplificadaRepository;
    private final RenunciaFiscalRepository renunciaFiscalRepository;
    private final OrdererService ordererService;
    private final MapperService mapperService;

    private static final int PODER_LEVEL = 0;
    private static final int MINISTERIO_LEVEL = 1;
    private static final int ORGAO_LEVEL = 2;
    private static final int UNIDADE_GESTORA_LEVEL = 3;
    private static final int ELEMENTO_DESPESA_LEVEL = 4;

    public List<DisplayableElementDTO> getPodereByUnidadeFederativa(Long unidadeFederativaId) {
        List<Poder> poderList = poderRepository.findByUnidadeFederativaUnidadeFederativaId(unidadeFederativaId);
        List<DisplayableElementDTO> displayableElementDTOList = new ArrayList<>();
        for (Poder poder : poderList) {
            displayableElementDTOList.add(mapperService.mapToDisplayableElementDto(poder.getPoderId(), poder.getNamePoder(),
                    poder.getTotalValueSpent(), poder.getPercentageOfTotal(), PODER_LEVEL));
        }
        return displayableElementDTOList;
    }

    public List<DisplayableElementDTO> getMinisterioByPoderId(Long poderId) {
        List<Ministerio> ministerioList = ministerioRepository.findByPoderPoderId(poderId);
        List<DisplayableElementDTO> displayableElementDTOList = new ArrayList<>();
        for (Ministerio ministerio : ministerioList) {
            displayableElementDTOList.add(mapperService.mapToDisplayableElementDto(ministerio.getMinisterioId(), ministerio.getNameMinisterio(),
                    ministerio.getTotalValueSpent(), ministerio.getPercentageOfTotal(), MINISTERIO_LEVEL));
        }
        return displayableElementDTOList;
    }

    public List<DisplayableElementDTO> getOrgaoByMinisterioId(Long ministerioId) {
        List<Orgao> orgaoList = orgaoRepository.findByMinisterioMinisterioId(ministerioId);

        if (orgaoList.size() == 1) {
            List<DisplayableElementDTO> unidadeGestoraList = getUnidadeGestoraByOrgaoId(orgaoList.getFirst().getOrgaoId());
            if (unidadeGestoraList.size() == 1 && Objects.equals(orgaoList.getFirst().getNameOrgao(), unidadeGestoraList.getFirst().getName())) {
                return getElementoDespesaByUnidadeGestoraId(unidadeGestoraList.getFirst().getId());
            } else {
                return unidadeGestoraList;
            }
        }

        List<DisplayableElementDTO> displayableElementDTOList = new ArrayList<>();
        for (Orgao orgao : orgaoList) {
            displayableElementDTOList.add(mapperService.mapToDisplayableElementDto(orgao.getOrgaoId(), orgao.getNameOrgao(),
                    orgao.getTotalValueSpent(), orgao.getPercentageOfTotal(), ORGAO_LEVEL));
        }
        return displayableElementDTOList;
    }

    public List<DisplayableElementDTO> getUnidadeGestoraByOrgaoId(Long orgaoId) {
        List<UnidadeGestora> unidadeGestoraList = unidadeGestoraRepository.findByOrgaoOrgaoId(orgaoId);

        if (unidadeGestoraList.size() == 1) {
            Orgao orgao = orgaoRepository.getReferenceById(orgaoId.toString());
            if (Objects.equals(orgao.getNameOrgao(), unidadeGestoraList.getFirst().getNameUnidadeGestora())) {
                return getElementoDespesaByUnidadeGestoraId(unidadeGestoraList.getFirst().getUnidadeGestoraId());
            }
        }

        List<DisplayableElementDTO> displayableElementDTOList = new ArrayList<>();
        for (UnidadeGestora unidadeGestora : unidadeGestoraList) {
            displayableElementDTOList.add(mapperService.mapToDisplayableElementDto(unidadeGestora.getUnidadeGestoraId(), unidadeGestora.getNameUnidadeGestora(),
                    unidadeGestora.getTotalValueSpent(), unidadeGestora.getPercentageOfTotal(), UNIDADE_GESTORA_LEVEL));
        }
        return displayableElementDTOList;
    }

    public List<DisplayableElementDTO> getElementoDespesaByUnidadeGestoraId(Long unidadeGestoraId) {
        List<ElementoDespesa> elementoDespesaList = elementoDespesaRepository.findByUnidadeGestoraUnidadeGestoraId(unidadeGestoraId);
        List<DisplayableElementDTO> displayableElementDTOList = new ArrayList<>();
        for (ElementoDespesa elementoDespesa : elementoDespesaList) {
            displayableElementDTOList.add(mapperService.mapToDisplayableElementDto(elementoDespesa.getElementoDespesaId(), elementoDespesa.getNameElementoDespesa(),
                    elementoDespesa.getTotalValueSpent(), elementoDespesa.getPercentageOfTotal(), ELEMENTO_DESPESA_LEVEL));
        }
        return displayableElementDTOList;
    }

    public List<DespesaSimplificada> getSimplifiedReport(Long unidadeFederativaId) {
        List<DespesaSimplificada> despesaSimplificadaList = despesaSimplificadaRepository.findByUnidadeFederativa(unidadeFederativaId);
        ordererService.orderDespesaSimplificadaListBySpending(despesaSimplificadaList);
        return despesaSimplificadaList;
    }

    public Double getTotalValueSpentByUnidadeFederativaId(Long unidadeFederativaId) {
        return unidadeFederativaRepository.findTotalValueSpentByUnidadeFederativaId(unidadeFederativaId);
    }

    public PaginatedResponse<RenunciaFiscal> getRenunciaFiscalByUnidadeFederativaId(Long unidadeFederativaId, int page, int size){
        Pageable pageable = PageRequest.of(page, size);

        Page<RenunciaFiscal> pageRenuncias = renunciaFiscalRepository.findByUnidadeFederativa(unidadeFederativaId, pageable);

        return new PaginatedResponse<>(
                pageRenuncias.getContent(),
                pageRenuncias.getTotalElements(),
                pageRenuncias.getTotalPages(),
                pageRenuncias.getNumber()
        );
    }

    public ValorTotal getRenunciaFiscalTotalByUnidadeFederativaId(Long unidadeFederativaId){
        return renunciaFiscalRepository.findTotalByUnidadeFederativa(unidadeFederativaId);
    }

}
