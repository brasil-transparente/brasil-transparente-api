package com.brasil.transparente.api.util;

import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.entity.ElementoDespesa;
import com.brasil.transparente.api.entity.Ministerio;
import com.brasil.transparente.api.entity.Orgao;
import com.brasil.transparente.api.entity.Poder;
import com.brasil.transparente.api.entity.UnidadeGestora;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class MapperService {

    @Mapping(source = "poderId", target = "id")
    @Mapping(source = "namePoder", target = "name")
    @Mapping(target = "level", expression = "java(level)")
    public abstract DisplayableElementDTO toDisplayableElementDTO(Poder poder, @Context int level);

    @Mapping(source = "ministerioId", target = "id")
    @Mapping(source = "nameMinisterio", target = "name")
    @Mapping(target = "level", expression = "java(level)")
    public abstract DisplayableElementDTO toDisplayableElementDTO(Ministerio ministerio, @Context int level);

    @Mapping(source = "orgaoId", target = "id")
    @Mapping(source = "nameOrgao", target = "name")
    @Mapping(target = "level", expression = "java(level)")
    public abstract DisplayableElementDTO toDisplayableElementDTO(Orgao orgao, @Context int level);

    @Mapping(source = "unidadeGestoraId", target = "id")
    @Mapping(source = "nameUnidadeGestora", target = "name")
    @Mapping(target = "level", expression = "java(level)")
    public abstract DisplayableElementDTO toDisplayableElementDTO(UnidadeGestora unidadeGestora, @Context int level);

    @Mapping(source = "elementoDespesaId", target = "id")
    @Mapping(source = "nameElementoDespesa", target = "name")
    @Mapping(target = "level", expression = "java(level)")
    public abstract DisplayableElementDTO toDisplayableElementDTO(ElementoDespesa elementoDespesa, @Context int level);
}
