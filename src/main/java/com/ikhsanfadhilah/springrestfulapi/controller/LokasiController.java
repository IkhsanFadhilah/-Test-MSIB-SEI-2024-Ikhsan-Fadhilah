package com.ikhsanfadhilah.springrestfulapi.controller;

import com.ikhsanfadhilah.springrestfulapi.dto.LokasiDTO;
import com.ikhsanfadhilah.springrestfulapi.mapper.LokasiMapper;
import com.ikhsanfadhilah.springrestfulapi.model.Lokasi;
import com.ikhsanfadhilah.springrestfulapi.service.LokasiService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@RestController
@RequestMapping("/api/lokasi")
public class LokasiController {
    @Autowired
    private LokasiService lokasiService;

    @Autowired
    private LokasiMapper lokasiMapper;

    @PostMapping
    public ResponseEntity<Lokasi> createLokasi(@RequestBody Lokasi lokasi) {
        return new ResponseEntity<>(lokasiService.saveLokasi(lokasi), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<LokasiDTO>> getAllLokasi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Lokasi> lokasiPage = lokasiService.getAllLokasi(PageRequest.of(page, size));
        Page<LokasiDTO> lokasiDTOPage = lokasiPage.map(lokasiMapper::toDTO);
        return ResponseEntity.ok(lokasiDTOPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lokasi> getLokasiById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(lokasiService.getLokasiById(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lokasi> updateLokasi(@PathVariable Long id, @RequestBody Lokasi lokasi) {
        try {
            return ResponseEntity.ok(lokasiService.updateLokasi(id, lokasi));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLokasi(@PathVariable Long id) {
        try {
            lokasiService.deleteLokasi(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Lokasi>> searchLokasiByNama(@RequestParam String nama) {
        return ResponseEntity.ok(lokasiService.findLokasiByNamaContaining(nama));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countLokasi() {
        return ResponseEntity.ok(lokasiService.countLokasi());
    }
}