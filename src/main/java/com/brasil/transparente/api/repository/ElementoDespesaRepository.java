package com.brasil.transparente.api.repository;

import com.brasil.transparente.api.entity.ElementoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElementoDespesaRepository extends JpaRepository<ElementoDespesa, String> {

    List<ElementoDespesa> findByUnidadeGestoraUnidadeGestoraId(Long unidadeGestoraId);

}
