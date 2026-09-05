package com.brasil.transparente.api.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "elemento_despesa")
class ElementoDespesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var elementoDespesaId: Long? = null

    var nameElementoDespesa: String? = null

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "unidade_gestora_id", referencedColumnName = "unidadeGestoraId")
    var unidadeGestora: UnidadeGestora? = null

    var totalValueSpent: Double? = null

    var percentageOfTotal: Double? = null

}