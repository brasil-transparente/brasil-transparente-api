package com.brasil.transparente.api.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "unidade_gestora")
class UnidadeGestora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var unidadeGestoraId: Long? = null

    var nameUnidadeGestora: String? = null

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "orgao_id", referencedColumnName = "orgaoId")
    var orgao: Orgao? = null

    @JsonManagedReference
    @OneToMany(mappedBy = "unidadeGestora", cascade = [CascadeType.ALL], orphanRemoval = true)
    var listElementDespesa: MutableList<ElementoDespesa>? = null

    var totalValueSpent: Double? = null

    var percentageOfTotal: Double? = null

}