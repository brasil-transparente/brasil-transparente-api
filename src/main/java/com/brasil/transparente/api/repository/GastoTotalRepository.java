package com.brasil.transparente.api.repository;

import com.brasil.transparente.api.entity.GastoTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GastoTotalRepository extends JpaRepository<GastoTotal, String> {
}
