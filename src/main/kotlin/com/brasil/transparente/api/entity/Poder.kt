package com.brasil.transparente.api.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "poder")
class Poder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var poderId: Long? = null

    var namePoder: String? = null

    @OneToMany(mappedBy = "poder", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonManagedReference
    var listMinisterio: MutableList<Ministerio>? = null

    var totalValueSpent: Double? = null

    var percentageOfTotal: Double? = null

}
