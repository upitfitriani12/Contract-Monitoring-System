package com.app.controller;

import com.app.model.Jabatan;
import com.app.service.JabatanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jabatan")
public class JabatanController {

    @Autowired
    private JabatanService jabatanService;

    @GetMapping
    public ResponseEntity<List<Jabatan>> getAllJabatan() {
        List<Jabatan> jabatans = jabatanService.getAllJabatan();
        return new ResponseEntity<>(jabatans, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jabatan> getJabatanById(@PathVariable Long id) {
        return jabatanService.getJabatanById(id)
                .map(jabatan -> new ResponseEntity<>(jabatan, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Jabatan> createJabatan(@RequestBody Jabatan jabatan) {
        Jabatan savedJabatan = jabatanService.saveJabatan(jabatan);
        return new ResponseEntity<>(savedJabatan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jabatan> updateJabatan(@PathVariable Long id, @RequestBody
    Jabatan jabatan) {
        return jabatanService.getJabatanById(id)
                .map(existingJabatan -> {
                    existingJabatan.setNamaJabatan(jabatan.getNamaJabatan());
                    Jabatan updatedJabatan = jabatanService.saveJabatan(existingJabatan);
                    return new ResponseEntity<>(updatedJabatan, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteJabatan(@PathVariable Long id) {
        jabatanService.deleteJabatan(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}