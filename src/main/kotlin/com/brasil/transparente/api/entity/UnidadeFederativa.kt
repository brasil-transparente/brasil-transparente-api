package com.brasil.transparente.api.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "unidade_federativa")
class UnidadeFederativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var unidadeFederativaId: Long? = null

    var nameUnidadeFederativa: String? = null

    var totalValueSpent: Double? = null

    @OneToMany(mappedBy = "unidadeFederativa", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var listPoder: MutableList<Poder>? = null

    constructor() {
        this.listPoder = ArrayList()
        this.totalValueSpent = 0.0
    }

    constructor(unidadeFederativa: String) {
        this.nameUnidadeFederativa = nameUnidadeFederativa
        this.listPoder = ArrayList()
        this.totalValueSpent = 0.0
    }

}