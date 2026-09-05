package com.brasil.transparente.api.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "orgao")
class Orgao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var orgaoId: Long? = null

    var nameOrgao: String? = null

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "ministerio_id", referencedColumnName = "ministerioId")
    var ministerio: Ministerio? = null

    @JsonManagedReference
    @OneToMany(mappedBy = "orgao", cascade = [CascadeType.ALL], orphanRemoval = true)
    var listUnidadeGestora: MutableList<UnidadeGestora>? = null

    var totalValueSpent: Double? = null

    var percentageOfTotal: Double? = null

}