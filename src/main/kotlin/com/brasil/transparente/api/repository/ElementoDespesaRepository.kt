package com.brasil.transparente.api.repository

import com.brasil.transparente.api.entity.ElementoDespesa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ElementoDespesaRepository : JpaRepository<ElementoDespesa, Long> {

    fun findByUnidadeGestoraUnidadeGestoraId(unidadeGestoraId: Long?): List<ElementoDespesa>

}