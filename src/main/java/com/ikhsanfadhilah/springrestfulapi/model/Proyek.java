package com.ikhsanfadhilah.springrestfulapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Proyek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama lokasi tidak boleh kosong")
    @Size(max = 100, message = "Nama lokasi maksimal 100 karakter")
    private String nama;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    @Size(max = 255, message = "Deskripsi maksimal 255 karakter")
    private String deskripsi;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "lokasi_id", insertable = false, updatable = false)
    private Lokasi lokasi;

    @Column(name = "lokasi_id")
    private Long lokasiId;

    public Proyek() {
    }

    public Proyek(String nama, String deskripsi, Lokasi lokasi) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.lokasi = lokasi;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Lokasi getLokasi() {
        return lokasi;
    }

    public void setLokasi(Lokasi lokasi) {
        this.lokasi = lokasi;
    }

    public void setLokasiId(Long lokasiId) {
        if (lokasiId != null) {
            Lokasi lokasi = new Lokasi();
            lokasi.setId(lokasiId);
            this.lokasi = lokasi;
        } else {
            this.lokasi = null;
        }
    }
    
    public Long getLokasiId() {
        return (lokasi != null) ? lokasi.getId() : null;
    }

    @Override
    public String toString() {
        return "Proyek{" +
                "id=" + id +
                ", nama='" + nama + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                ", lokasi=" + lokasi +
                '}';
    }
}