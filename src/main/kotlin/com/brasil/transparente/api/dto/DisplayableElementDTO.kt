package com.brasil.transparente.api.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Elemento genérico da hierarquia administrativa brasileira. Dependendo do contexto do endpoint, pode representar um Poder, Ministério, Órgão ou Unidade Gestora.")
data class DisplayableElementDTO(
    @field:Schema(description = "Identificador único do elemento")
    var id: Long? = null,
    @field:Schema(description = "Nome oficial da entidade administrativa")
    var name: String? = null,
    @field:Schema(description = "Valor total efetivamente pago no período analisado, em BRL")
    var totalValueSpent: Double? = null,
    @field:Schema(description = "Percentual do total do nível pai representado por este elemento")
    var percentageOfTotal: Double? = null,
    @field:Schema(description = "Indicador do nível hierárquico: 1=Unidade Federativa, 2=Poder, 3=Ministério, 4=Órgão, 5=Unidade Gestora")
    var level: Int? = null,
)