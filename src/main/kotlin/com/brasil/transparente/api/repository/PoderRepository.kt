package com.brasil.transparente.api.repository

import com.brasil.transparente.api.entity.Poder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PoderRepository : JpaRepository<Poder, Long> {

    @Query("SELECT COALESCE(SUM(p.totalValueSpent), 0.0) FROM Poder p")
    fun getTotalValueSpent(): Double

}
