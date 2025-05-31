package com.itau.ms_desafio_itau.service;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.repository.AutomovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutomovelService implements IAutomovelService {

    @Autowired
    private static AutomovelRepository repository;

    public AutomovelService(AutomovelRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(AutomovelRequestDTO requestDTOdto) {

    }

    @Override
    public AutomovelResponseDTO listaAll() {
        return null;
    }

    @Override
    public AutomovelResponseDTO findById(Integer id) {
        return null;
    }
}
