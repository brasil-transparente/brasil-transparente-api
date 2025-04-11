package com.brasil.transparente.api.repository;

import com.brasil.transparente.api.entity.Ministerio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MinisterioRepository extends JpaRepository<Ministerio, String> {

    List<Ministerio> findByPoderPoderId(Long poderId);

}
