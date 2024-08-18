package com.ikhsanfadhilah.springrestfulapi.repository;

import com.ikhsanfadhilah.springrestfulapi.model.Proyek;
import com.ikhsanfadhilah.springrestfulapi.model.Lokasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Long> {
    Optional<Proyek> findByNama(String nama);
    List<Proyek> findByNamaContaining(String keyword);
    List<Proyek> findByDeskripsiContaining(String keyword);
    List<Proyek> findByLokasi(Lokasi lokasi);
    List<Proyek> findByLokasiId(Long lokasiId);
    long countByLokasi(Lokasi lokasi);
    boolean existsByNama(String nama);
}