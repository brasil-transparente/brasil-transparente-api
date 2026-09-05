package com.brasil.transparente.api.repository

import com.brasil.transparente.api.entity.Ministerio
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MinisterioRepository : JpaRepository<Ministerio, String> {

    fun findByPoderPoderId(poderId : Long) : List<Ministerio>

}