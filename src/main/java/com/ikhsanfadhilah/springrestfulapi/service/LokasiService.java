package com.ikhsanfadhilah.springrestfulapi.service;

import com.ikhsanfadhilah.springrestfulapi.model.Lokasi;
import com.ikhsanfadhilah.springrestfulapi.repository.LokasiRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class LokasiService {
    private static final Logger logger = LoggerFactory.getLogger(LokasiService.class);

    @Autowired
    private LokasiRepository lokasiRepository;

    public Page<Lokasi> getAllLokasi(Pageable pageable) {
        return lokasiRepository.findAll(pageable);
    }

    public Lokasi getLokasiById(Long id) {
        return lokasiRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lokasi tidak ditemukan dengan id: " + id));
    }

    public Lokasi saveLokasi(Lokasi lokasi) {
        logger.info("Saving new lokasi: {}", lokasi.getNama());
        return lokasiRepository.save(lokasi);
    }

    public Lokasi updateLokasi(Long id, Lokasi lokasi) {
        Lokasi existingLokasi = getLokasiById(id);
        existingLokasi.setNama(lokasi.getNama());
        existingLokasi.setAlamat(lokasi.getAlamat());
        return lokasiRepository.save(existingLokasi);
    }

    public void deleteLokasi(Long id) {
        if (!lokasiRepository.existsById(id)) {
            throw new EntityNotFoundException("Lokasi tidak ditemukan dengan id: " + id);
        }
        lokasiRepository.deleteById(id);
    }

    public List<Lokasi> findLokasiByNamaContaining(String keyword) {
        return lokasiRepository.findByNamaContaining(keyword);
    }

    public Optional<Lokasi> findLokasiByNama(String nama) {
        return lokasiRepository.findByNama(nama);
    }

    public long countLokasi() {
        return lokasiRepository.count();
    }
}