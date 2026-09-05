package com.brasil.transparente.api.repository

import com.brasil.transparente.api.entity.DespesaSimplificada
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface DespesaSimplificadaRepository : JpaRepository<DespesaSimplificada, String> {

    @Query("SELECT ds FROM DespesaSimplificada ds " +
            "WHERE ds.unidadeFederativaId = :unidadeFederativaId")
    fun findByUnidadeFederativa(@Param("unidadeFederativaId") unidadeFederativaId: Long): MutableList<DespesaSimplificada>

}