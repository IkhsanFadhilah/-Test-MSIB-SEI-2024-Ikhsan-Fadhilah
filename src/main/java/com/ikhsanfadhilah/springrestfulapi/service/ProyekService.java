package com.ikhsanfadhilah.springrestfulapi.service;

import com.ikhsanfadhilah.springrestfulapi.model.Proyek;
import com.ikhsanfadhilah.springrestfulapi.dto.ProyekDTO;
import com.ikhsanfadhilah.springrestfulapi.model.Lokasi;
import com.ikhsanfadhilah.springrestfulapi.repository.ProyekRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import com.ikhsanfadhilah.springrestfulapi.repository.LokasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class ProyekService {
    @Autowired
    private ProyekRepository proyekRepository;

    @Autowired
    private LokasiRepository lokasiRepository;

    public List<Proyek> getAllProyek() {
        return proyekRepository.findAll();
    }

    public Proyek getProyekById(Long id) {
        return proyekRepository.findById(id)
                .orElseThrow(() -> new EntityExistsException("Proyek tidak ditemukan dengan id: " + id));
    }

    public Proyek saveProyek(ProyekDTO proyekDTO) {
        Proyek proyek = new Proyek();
        proyek.setNama(proyekDTO.getNama());
        proyek.setDeskripsi(proyekDTO.getDeskripsi());

        Lokasi lokasi = lokasiRepository.findById(proyekDTO.getLokasiId())
                            .orElseThrow(() -> new EntityNotFoundException("Lokasi not found"));
        proyek.setLokasi(lokasi);

        return proyekRepository.save(proyek);
    }

    public Proyek updateProyek(Long id, Proyek proyek) {
        Proyek existingProyek = getProyekById(id);
        existingProyek.setNama(proyek.getNama());
        existingProyek.setDeskripsi(proyek.getDeskripsi());
        if (proyek.getLokasi() != null && proyek.getLokasi().getId() != null) {
            Lokasi lokasi = lokasiRepository.findById(proyek.getLokasi().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Lokasi tidak ditemukan dengan id: " + proyek.getLokasi().getId()));
            existingProyek.setLokasi(lokasi);
        }
        return proyekRepository.save(existingProyek);
    }

    public void deleteProyek(Long id) {
        if (!proyekRepository.existsById(id)) {
            throw new EntityNotFoundException("Proyek tidak ditemukan dengan id: " + id);
        }
        proyekRepository.deleteById(id);
    }

    public List<Proyek> findProyekByNamaContaining(String keyword) {
        return proyekRepository.findByNamaContaining(keyword);
    }

    public List<Proyek> findProyekByLokasi(Lokasi lokasi) {
        return proyekRepository.findByLokasi(lokasi);
    }

    public long countProyek() {
        return proyekRepository.count();
    }

    public List<Proyek> findProyekByLokasiId(Long lokasiId) {
        return proyekRepository.findByLokasiId(lokasiId);
    }
}