package com.brasil.transparente.api.repository

import com.brasil.transparente.api.entity.Orgao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrgaoRepository : JpaRepository<Orgao, String> {

    fun findByMinisterioMinisterioId(ministerioId : Long) : List<Orgao>

}