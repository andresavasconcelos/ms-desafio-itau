package com.itau.ms_desafio_itau.service;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;

import java.util.List;

public interface IAutomovelService {

    void create(AutomovelRequestDTO requestDTOdto);

    List<AutomovelResponseDTO> listaAll();

    AutomovelResponseDTO searchById(Long id);

    void update(Long id, AutomovelRequestDTO dto);

    void delete(long id);

}
