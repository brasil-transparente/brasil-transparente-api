package com.brasil.transparente.api.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "ministerio")
class Ministerio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var ministerioId: Long? = null

    var nameMinisterio: String? = null

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "poder_id", referencedColumnName = "poderId")
    var poder: Poder? = null

    @OneToMany(mappedBy = "ministerio", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var listOrgao: MutableList<Orgao>? = null

    var totalValueSpent: Double? = null

    var percentageOfTotal: Double? = null

}