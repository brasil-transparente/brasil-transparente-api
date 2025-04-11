package com.brasil.transparente.api.repository;

import com.brasil.transparente.api.entity.Orgao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrgaoRepository extends JpaRepository<Orgao, String> {

    List<Orgao> findByMinisterioMinisterioId(Long ministerioId);

}
