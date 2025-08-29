package com.app.controller;

import com.app.dto.PegawaiDTO;
import com.app.model.Pegawai;
import com.app.service.PegawaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pegawai")
public class PegawaiController {

    @Autowired
    private PegawaiService pegawaiService;

    @GetMapping
    public ResponseEntity<List<PegawaiDTO>> getAllPegawai() {
        List<PegawaiDTO> dtos = pegawaiService.getAllPegawai().stream().map(p -> {
            PegawaiDTO dto = new PegawaiDTO();
            dto.kodePegawai = p.getKodePegawai();
            dto.namaPegawai = p.getNamaPegawai();
            dto.namaJabatan = p.getJabatan().getNamaJabatan();
            dto.namaCabang = p.getCabang().getNamaCabang();
            dto.tanggalMulaiKontrak = p.getTanggalMulaiKontrak();
            dto.tanggalHabisKontrak = p.getTanggalHabisKontrak();
            return dto;
        }).toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pegawai> getById(@PathVariable Long id) {
        Pegawai p = pegawaiService.getPegawaiById(id);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pegawai> create(@RequestBody Pegawai pegawai) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pegawaiService.savePegawai(pegawai));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pegawai> update(@PathVariable Long id, @RequestBody Pegawai pegawai) {
        Pegawai existing = pegawaiService.getPegawaiById(id);
        if (existing != null) {
            existing.setKodePegawai(pegawai.getKodePegawai());
            existing.setNamaPegawai(pegawai.getNamaPegawai());
            existing.setJabatan(pegawai.getJabatan());
            existing.setCabang(pegawai.getCabang());
            existing.setTanggalMulaiKontrak(pegawai.getTanggalMulaiKontrak());
            existing.setTanggalHabisKontrak(pegawai.getTanggalHabisKontrak());
            return ResponseEntity.ok(pegawaiService.savePegawai(existing));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pegawaiService.deletePegawai(id);
        return ResponseEntity.noContent().build();
    }
}
