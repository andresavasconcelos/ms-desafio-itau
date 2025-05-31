package com.itau.ms_desafio_itau.service;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.entities.Automovel;
import com.itau.ms_desafio_itau.mapper.AutomovelMapper;
import com.itau.ms_desafio_itau.repository.AutomovelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AutomovelServiceImpl implements IAutomovelService {

    private static final Logger log = LoggerFactory.getLogger(AutomovelServiceImpl.class);

    private final AutomovelRepository repository;
    private final AutomovelMapper mapper;

    public AutomovelServiceImpl(AutomovelRepository repository, AutomovelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void create(AutomovelRequestDTO requestDTO) {
//        TODO: inserir validação de nome unico
//        TODO: implementar erros de validação
        Automovel automovel = mapper.toEntity(requestDTO);
        repository.save(automovel);
    }

    @Override
    public AutomovelResponseDTO listaAll() {
//        TODO: implementar
        return null;
    }

    @Override
    public AutomovelResponseDTO findById(Integer id) {
//        TODO: implementar
        return null;
    }
}
