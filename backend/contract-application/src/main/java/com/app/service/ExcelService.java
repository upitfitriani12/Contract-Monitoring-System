package com.app.service;

import com.app.model.Cabang;
import com.app.model.Jabatan;
import com.app.model.Pegawai;
import com.app.repository.CabangRepository;
import com.app.repository.JabatanRepository;
import com.app.repository.PegawaiRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;

@Service
public class ExcelService {

    @Autowired
    private PegawaiRepository pegawaiRepository;
    @Autowired
    private JabatanRepository jabatanRepository;
    @Autowired
    private CabangRepository cabangRepository;

    public void saveExcelData(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Lewati header
            if (rows.hasNext()) rows.next();

            DataFormatter formatter = new DataFormatter();

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // --- Kode Pegawai ---
                String kodePegawai = formatter.formatCellValue(currentRow.getCell(0)).trim();
                if (kodePegawai.isEmpty()) continue;

                // --- Nama Pegawai ---
                String namaPegawai = formatter.formatCellValue(currentRow.getCell(1)).trim();
                if (namaPegawai.isEmpty()) continue;

                // --- Nama Jabatan ---
                String namaJabatan = formatter.formatCellValue(currentRow.getCell(2)).trim();
                if (namaJabatan.isEmpty()) continue;

                Jabatan jabatan = jabatanRepository.findByNamaJabatan(namaJabatan)
                        .orElseGet(() -> jabatanRepository.save(Jabatan.builder()
                                .namaJabatan(namaJabatan)
                                .build()));

                // --- Nama Cabang ---
                String namaCabang = formatter.formatCellValue(currentRow.getCell(3)).trim();
                if (namaCabang.isEmpty()) continue;

                Cabang cabang = cabangRepository.findByNamaCabang(namaCabang)
                        .orElseGet(() -> cabangRepository.save(Cabang.builder()
                                .namaCabang(namaCabang)
                                .build()));

                // --- Tanggal Mulai Kontrak ---
                LocalDate tglMulai = null;
                String tglMulaiStr = formatter.formatCellValue(currentRow.getCell(4)).trim();
                if (!tglMulaiStr.isEmpty()) {
                    try {
                        tglMulai = LocalDate.parse(tglMulaiStr); // yyyy-MM-dd
                    } catch (Exception e) {
                        tglMulai = LocalDate.parse(tglMulaiStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    }
                }
                if (tglMulai == null) continue;

                // --- Tanggal Habis Kontrak ---
                LocalDate tglHabis = null;
                String tglHabisStr = formatter.formatCellValue(currentRow.getCell(5)).trim();
                if (!tglHabisStr.isEmpty()) {
                    try {
                        tglHabis = LocalDate.parse(tglHabisStr);
                    } catch (Exception e) {
                        tglHabis = LocalDate.parse(tglHabisStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    }
                }
                if (tglHabis == null) continue;

                // --- Simpan Pegawai ---
                Pegawai pegawai = Pegawai.builder()
                        .kodePegawai(kodePegawai)
                        .namaPegawai(namaPegawai)
                        .jabatan(jabatan)
                        .cabang(cabang)
                        .tanggalMulaiKontrak(tglMulai)
                        .tanggalHabisKontrak(tglHabis)
                        .build();

                pegawaiRepository.save(pegawai);
            }

        } catch (Exception e) {
            throw new RuntimeException("Gagal mengurai file Excel: " + e.getMessage(), e);
        }
    }
}
