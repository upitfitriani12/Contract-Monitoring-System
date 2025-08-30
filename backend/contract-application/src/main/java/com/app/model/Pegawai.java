package com.app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pegawai")
public class Pegawai {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPegawai;
    @Column(unique = true, nullable = false)
    private String kodePegawai;
    @Column(nullable = false)
    private String namaPegawai;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_jabatan", nullable = false)
    @JsonIgnoreProperties("pegawaiList")
    private Jabatan jabatan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cabang", nullable = false)
    @JsonIgnoreProperties("pegawaiList")
    private Cabang cabang;

    @Column(nullable = false)
    private LocalDate tanggalMulaiKontrak;
    @Column(nullable = false)
    private LocalDate tanggalHabisKontrak;
}