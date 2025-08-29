package com.app.service;

import com.app.model.Pegawai;
import com.app.repository.PegawaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PegawaiService {

    @Autowired
    private PegawaiRepository pegawaiRepository;

    public List<Pegawai> getAllPegawai() {
        return pegawaiRepository.findAll();
    }

    public Pegawai getPegawaiById(Long id) {
        return pegawaiRepository.findById(id).orElse(null);
    }

    public Pegawai savePegawai(Pegawai pegawai) {
        return pegawaiRepository.save(pegawai);
    }

    public void deletePegawai(Long id) {
        pegawaiRepository.deleteById(id);
    }

    public List<Map<String, Object>> getPegawaiHabisKontrak(String namaPegawai, String kodePegawai, String namaJabatan, String namaCabang, Integer daysUntilExpiration) {
        List<Object[]> results = pegawaiRepository.findPegawaiHabisKontrak(namaPegawai, kodePegawai, namaJabatan, namaCabang, daysUntilExpiration);

        List<Map<String, Object>> mappedResults = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = Map.of(
                    "kodePegawai", row[0],
                    "namaPegawai", row[1],
                    "namaCabang", row[2],
                    "namaJabatan", row[3],
                    "tanggalMulaiKontrak", row[4],
                    "tanggalHabisKontrak", row[5]
            );
            mappedResults.add(map);
        }
        return mappedResults;
    }
}
