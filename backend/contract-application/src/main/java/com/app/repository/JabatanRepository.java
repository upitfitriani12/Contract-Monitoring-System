package com.app.repository;

import com.app.model.Jabatan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JabatanRepository extends JpaRepository<Jabatan, Long> {
    Optional<Jabatan> findByNamaJabatan(String namaJabatan);
}
