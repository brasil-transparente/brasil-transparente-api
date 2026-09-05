package com.brasil.transparente.api.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "despesa_simplificada")
@Schema(description = "Registro de gasto público simplificado e reclassificado, agrupado de acordo com a metodologia do projeto para agregar despesas comparáveis")
class DespesaSimplificada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @field:Schema(description = "Identificador único do registro de gasto simplificado")
    var despesaSimplificadaId: Long? = null

    @field:Schema(description = "Nome da categoria de gasto reclassificada")
    var name: String? = null

    @field:Schema(description = "Valor total efetivamente pago para esta categoria, em BRL")
    var totalValue: Double? = null

    @field:Schema(description = "Percentual do total de gastos da unidade pela categoria")
    var percentageOfTotal: Double? = null

    @field:Schema(description = "Identificador da Unidade Federativa à qual este registro de gasto pertence. Use 1 para o Governo Federal")
    var unidadeFederativaId: Long? = null

}