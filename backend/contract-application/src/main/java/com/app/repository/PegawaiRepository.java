package com.app.repository;

import com.app.model.Pegawai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PegawaiRepository extends JpaRepository<Pegawai, Long> {

    // Memanggil stored procedure GetPegawaiHabisKontrak
    @Query(value = "CALL GetPegawaiHabisKontrak(:namaPegawai, :kodePegawai, :namaJabatan, :namaCabang, :daysUntilExpiration)", nativeQuery = true)
    List<Object[]> findPegawaiHabisKontrak(
            @Param("namaPegawai") String namaPegawai,
            @Param("kodePegawai") String kodePegawai,
            @Param("namaJabatan") String namaJabatan,
            @Param("namaCabang") String namaCabang,
            @Param("daysUntilExpiration") Integer daysUntilExpiration
    );
}
