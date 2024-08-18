package com.ikhsanfadhilah.springrestfulapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProyekDTO {
    private Long id;

    @NotBlank(message = "Nama proyek tidak boleh kosong")
    @Size(max = 100, message = "Nama proyek maksimal 100 karakter")
    private String nama;

    @Size(max = 500, message = "Deskripsi maksimal 500 karakter")
    private String deskripsi;

    @NotNull(message = "ID lokasi tidak boleh kosong")
    private Long lokasiId;

    public ProyekDTO() {
    }

    public ProyekDTO(Long id, String nama, String deskripsi, Long lokasiId) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.lokasiId = lokasiId;
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

    public Long getLokasiId() {
        return lokasiId;
    }

    public void setLokasiId(Long lokasiId) {
        this.lokasiId = lokasiId;
    }
}