package com.itau.ms_desafio_itau.mapper;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.entities.Automovel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutomovelMapper {

    Automovel toEntity(AutomovelRequestDTO dto);
    AutomovelResponseDTO toResponseDTO(Automovel automovel);
    List<AutomovelResponseDTO> toResponseListDTO(List<Automovel> automoveis);
}
