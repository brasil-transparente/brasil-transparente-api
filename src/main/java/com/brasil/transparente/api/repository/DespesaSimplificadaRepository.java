package com.brasil.transparente.api.repository;

import com.brasil.transparente.api.entity.DespesaSimplicada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaSimplificadaRepository extends JpaRepository<DespesaSimplicada, String> {

}
