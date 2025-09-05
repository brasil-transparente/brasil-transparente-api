package com.brasil.transparente.api.repository;

import com.brasil.transparente.api.entity.RenunciaFiscal;
import com.brasil.transparente.api.entity.ValorTotal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RenunciaFiscalRepository extends JpaRepository<RenunciaFiscal, String> {

    @Query("SELECT rf FROM RenunciaFiscal rf " +
            "WHERE rf.unidadeFederativaId = :unidadeFederativaId " +
            "ORDER BY rf.valorRenunciado DESC")
    Page<RenunciaFiscal> findByUnidadeFederativa(
            @Param("unidadeFederativaId") Long unidadeFederativaId,
            Pageable pageable
    );

    @Query(value = "SELECT rf.unidade_federativa_id AS unidadeFederativaId, " +
            "       rf.ano AS ano, " +
            "       SUM(rf.valor_renunciado) AS valorTotal " +
            "FROM renuncia_fiscal rf " +
            "WHERE rf.unidade_federativa_id = :unidadeFederativaId " +
            "GROUP BY rf.unidade_federativa_id, rf.ano " +
            "LIMIT 1",
            nativeQuery = true)
    ValorTotal findTotalByUnidadeFederativa(@Param("unidadeFederativaId") Long unidadeFederativaId);


}
