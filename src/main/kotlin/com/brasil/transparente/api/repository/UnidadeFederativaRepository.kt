package com.brasil.transparente.api.repository

import com.brasil.transparente.api.entity.UnidadeFederativa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface UnidadeFederativaRepository : JpaRepository<UnidadeFederativa, Long> {

    @Query("SELECT uf.totalValueSpent FROM UnidadeFederativa uf WHERE uf.unidadeFederativaId = :unidadeFederativaId")
    fun findTotalValueSpentByUnidadeFederativaId(@Param("unidadeFederativaId") unidadeFederativaId: Long) : Double

}