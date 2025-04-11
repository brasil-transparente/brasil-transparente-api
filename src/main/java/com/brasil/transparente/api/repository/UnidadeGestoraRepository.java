package com.brasil.transparente.api.repository;

import com.brasil.transparente.api.entity.UnidadeGestora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadeGestoraRepository extends JpaRepository<UnidadeGestora, String> {

    List<UnidadeGestora> findByOrgaoOrgaoId(Long orgaoId);

}
