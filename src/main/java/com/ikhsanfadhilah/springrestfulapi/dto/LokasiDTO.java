package com.ikhsanfadhilah.springrestfulapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LokasiDTO {
    private Long id;

    @NotBlank(message = "Nama lokasi tidak boleh kosong")
    @Size(max = 100, message = "Nama lokasi maksimal 100 karakter")
    private String nama;

    @NotBlank(message = "Alamat tidak boleh kosong")
    @Size(max = 255, message = "Alamat maksimal 255 karakter")
    private String alamat;

    public LokasiDTO() {
    }

    public LokasiDTO(Long id, String nama, String alamat) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
}