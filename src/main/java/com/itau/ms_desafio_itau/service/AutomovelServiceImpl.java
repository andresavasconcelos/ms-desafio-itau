package com.itau.ms_desafio_itau.service;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.entities.Automovel;
import com.itau.ms_desafio_itau.mapper.AutomovelMapper;
import com.itau.ms_desafio_itau.repository.AutomovelRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AutomovelServiceImpl implements com.itau.ms_desafio_itau.service.IAutomovelService {

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

        try {
            Automovel automovel = mapper.toEntity(requestDTO);
            repository.save(automovel);
        } catch (Exception ex) {
            throw new IllegalStateException("Erro ao salvar o automóvel: " + ex.getMessage());
        }
    }

    @Override
    public List<AutomovelResponseDTO> listaAll() {
        List<Automovel> automoveis = repository.findAll();
        if (automoveis.isEmpty()) throw new EntityNotFoundException("Nenhum automóvel encontrado.");

        return  mapper.toResponseListDTO(automoveis);
    }

    @Override
    public AutomovelResponseDTO findById(Long id) {
        Automovel automovel = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Automóvel não encontrado com id: " + id));

        return mapper.toResponseDTO(automovel);
    }
}
