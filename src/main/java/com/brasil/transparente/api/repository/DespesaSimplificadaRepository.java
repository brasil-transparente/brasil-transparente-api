package com.brasil.transparente.api.repository;

import com.brasil.transparente.api.entity.DespesaSimplificada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaSimplificadaRepository extends JpaRepository<DespesaSimplificada, String> {

    @Query("SELECT ds FROM DespesaSimplificada ds " +
            "WHERE ds.unidadeFederativaId = :unidadeFederativaId")
    List<DespesaSimplificada> findByUnidadeFederativa(
            @Param("unidadeFederativaId") Long unidadeFederativaId
    );


}
