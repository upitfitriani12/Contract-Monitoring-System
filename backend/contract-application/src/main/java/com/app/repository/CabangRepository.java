package com.app.repository;

import com.app.model.Cabang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CabangRepository extends JpaRepository<Cabang, Long> {
    Optional<Cabang> findByNamaCabang(String namaCabang);
}
