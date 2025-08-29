package com.app.controller;

import com.app.model.Cabang;
import com.app.service.CabangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cabang")
public class CabangController {

    @Autowired
    private CabangService cabangService;

    @GetMapping
    public ResponseEntity<List<Cabang>> getAllCabang() {
        List<Cabang> cabangs = cabangService.getAllCabang();
        return new ResponseEntity<>(cabangs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cabang> getCabangById(@PathVariable Long id) {
        return cabangService.getCabangById(id)
                .map(cabang -> new ResponseEntity<>(cabang, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Cabang> createCabang(@RequestBody Cabang cabang) {
        Cabang savedCabang = cabangService.saveCabang(cabang);
        return new ResponseEntity<>(savedCabang, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cabang> updateCabang(@PathVariable Long id, @RequestBody
    Cabang cabang) {
        return cabangService.getCabangById(id)
                .map(existingCabang -> {
                    existingCabang.setNamaCabang(cabang.getNamaCabang());
                    Cabang updatedCabang = cabangService.saveCabang(existingCabang);
                    return new ResponseEntity<>(updatedCabang, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCabang(@PathVariable Long id) {
        cabangService.deleteCabang(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}