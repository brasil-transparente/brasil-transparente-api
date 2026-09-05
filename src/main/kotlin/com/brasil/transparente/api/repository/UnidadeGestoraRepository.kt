package com.brasil.transparente.api.repository

import com.brasil.transparente.api.entity.UnidadeGestora
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UnidadeGestoraRepository : JpaRepository<UnidadeGestora, String> {

    fun findByOrgaoOrgaoId(orgaoId : Long?) : List<UnidadeGestora>

}