package com.brasil.transparente.api.repository;

import com.brasil.transparente.api.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, String> {

    List<Receita> findAllByOrderByTotalValueSpentDesc();

}
