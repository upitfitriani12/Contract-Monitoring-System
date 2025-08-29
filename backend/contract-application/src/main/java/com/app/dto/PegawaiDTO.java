package com.app.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PegawaiDTO {
    public String kodePegawai;
    public String namaPegawai;
    public String namaJabatan;
    public String namaCabang;
    public LocalDate tanggalMulaiKontrak;
    public LocalDate tanggalHabisKontrak;
}
