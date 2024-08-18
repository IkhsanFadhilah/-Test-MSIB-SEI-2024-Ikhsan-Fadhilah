package com.ikhsanfadhilah.springrestfulapi.controller;

import com.ikhsanfadhilah.springrestfulapi.dto.ProyekDTO;
import com.ikhsanfadhilah.springrestfulapi.model.Proyek;
import com.ikhsanfadhilah.springrestfulapi.service.ProyekService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/proyek")
public class ProyekController {
    @Autowired
    private ProyekService proyekService;

    @PostMapping
    public ResponseEntity<Proyek> createProyek(@RequestBody ProyekDTO proyekDTO) {
        return new ResponseEntity<>(proyekService.saveProyek(proyekDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Proyek>> getAllProyek() {
        return ResponseEntity.ok(proyekService.getAllProyek());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyek> getProyekById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(proyekService.getProyekById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyek> updateProyek(@PathVariable Long id, @RequestBody Proyek proyek) {
        try {
            return ResponseEntity.ok(proyekService.updateProyek(id, proyek));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyek(@PathVariable Long id) {
        try {
            proyekService.deleteProyek(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Proyek>> searchProyekByNama(@RequestParam String nama) {
        return ResponseEntity.ok(proyekService.findProyekByNamaContaining(nama));
    }

    @GetMapping("/lokasi/{lokasiId}")
    public ResponseEntity<List<Proyek>> getProyekByLokasiId(@PathVariable Long lokasiId) {
        return ResponseEntity.ok(proyekService.findProyekByLokasiId(lokasiId));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countProyek() {
        return ResponseEntity.ok(proyekService.countProyek());
    }
}