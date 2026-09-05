package com.brasil.transparente.api.repository

import com.brasil.transparente.api.entity.Poder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PoderRepository : JpaRepository<Poder, String> {

    fun findByUnidadeFederativaUnidadeFederativaId(unidadeFederativaId : Long) : List<Poder>

}