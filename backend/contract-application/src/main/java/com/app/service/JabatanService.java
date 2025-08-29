package com.app.service;

import com.app.model.Jabatan;
import com.app.repository.JabatanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JabatanService {

    @Autowired
    private JabatanRepository jabatanRepository;

    public List<Jabatan> getAllJabatan() {
        return jabatanRepository.findAll();
    }

    public Optional<Jabatan> getJabatanById(Long id) {
        return jabatanRepository.findById(id);
    }

    public Jabatan saveJabatan(Jabatan jabatan) {
        return jabatanRepository.save(jabatan);
    }

    public void deleteJabatan(Long id) {
        jabatanRepository.deleteById(id);
    }
}