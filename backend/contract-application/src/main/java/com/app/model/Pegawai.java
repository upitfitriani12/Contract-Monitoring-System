package com.app.model;

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
    @ManyToOne
    @JoinColumn(name = "id_jabatan", nullable = false)
    private Jabatan jabatan;
    @ManyToOne
    @JoinColumn(name = "id_cabang", nullable = false)
    private Cabang cabang;
    @Column(nullable = false)
    private LocalDate tanggalMulaiKontrak;
    @Column(nullable = false)
    private LocalDate tanggalHabisKontrak;
}