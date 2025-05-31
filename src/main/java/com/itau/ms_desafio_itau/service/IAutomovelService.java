package com.itau.ms_desafio_itau.service;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;

public interface IAutomovelService {

    void create(AutomovelRequestDTO requestDTOdto);

    AutomovelResponseDTO listaAll();

    AutomovelResponseDTO findById(Integer id);

}
