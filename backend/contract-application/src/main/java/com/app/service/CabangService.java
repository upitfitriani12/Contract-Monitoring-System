package com.app.service;

import com.app.model.Cabang;
import com.app.repository.CabangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CabangService {

    @Autowired
    private CabangRepository cabangRepository;

    public List<Cabang> getAllCabang() {
        return cabangRepository.findAll();
    }

    public Optional<Cabang> getCabangById(Long id) {
        return cabangRepository.findById(id);
    }

    public Cabang saveCabang(Cabang cabang) {
        return cabangRepository.save(cabang);
    }

    public void deleteCabang(Long id) {
        cabangRepository.deleteById(id);
    }
}