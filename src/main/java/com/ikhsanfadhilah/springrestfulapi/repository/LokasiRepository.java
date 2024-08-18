package com.ikhsanfadhilah.springrestfulapi.repository;

import com.ikhsanfadhilah.springrestfulapi.model.Lokasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Long> {

    Optional<Lokasi> findByNama(String nama);
    List<Lokasi> findByNamaContaining(String keyword);
    List<Lokasi> findByAlamat(String alamat);
    List<Lokasi> findByAlamatContaining(String keyword);
    long countByNama(String nama);
    boolean existsByNama(String nama);
}