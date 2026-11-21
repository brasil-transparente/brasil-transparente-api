package com.brasil.transparente.api.repository

import com.brasil.transparente.api.entity.DespesaSimplificada
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DespesaSimplificadaRepository : JpaRepository<DespesaSimplificada, Long>
