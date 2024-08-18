package com.ikhsanfadhilah.springrestfulapi.mapper;

import com.ikhsanfadhilah.springrestfulapi.dto.LokasiDTO;
import com.ikhsanfadhilah.springrestfulapi.model.Lokasi;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LokasiMapper {
    LokasiDTO toDTO(Lokasi lokasi);
    Lokasi toEntity(LokasiDTO lokasiDTO);
}