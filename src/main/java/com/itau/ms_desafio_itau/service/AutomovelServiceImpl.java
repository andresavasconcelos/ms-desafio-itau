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
        log.info("Iniciando criação de um novo automóvel com dados: {}", requestDTO);

        try {
            Automovel automovel = mapper.toEntity(requestDTO);
            repository.save(automovel);
            log.info("Automóvel salvo com sucesso: {}", automovel);
        } catch (Exception ex) {
            log.error("Erro ao salvar o automóvel", ex);
            throw new IllegalStateException("Erro ao salvar o automóvel: " + ex.getMessage());
        }
    }

    @Override
    public List<AutomovelResponseDTO> listaAll() {
        log.info("Buscando todos os automóveis");

        List<Automovel> automoveis = repository.findAll();
        if (automoveis.isEmpty()) {
            log.warn("Nenhum automóvel encontrado");
            throw new EntityNotFoundException("Nenhum automóvel encontrado");
        }

        log.info("Total de automóveis encontrados: {}", automoveis.size());
        return mapper.toResponseListDTO(automoveis);
    }

    @Override
    public AutomovelResponseDTO searchById(Long id) {
        log.info("Buscando automóvel com id: {}", id);

        Automovel automovel = findById(id);

        log.info("Autmóvel encontrado: {}", automovel);
        return mapper.toResponseDTO(automovel);
    }

    @Override
    public void update(Long id, AutomovelRequestDTO dto) {
        log.info("Atualizando automóvel");

        findById(id);
        Automovel autoToUpdate = mapper.toEntity(dto);
        autoToUpdate.setId(id);

        repository.save(autoToUpdate);
        log.info("Automovel atualizado com sucesso!");
    }

    @Override
    public void delete(long id) {
        log.info("Deletando automóvel com id: {}", id);

        Automovel automovel = findById(id);

        repository.deleteById(automovel.getId());
        log.info("Autmóvel deletado com sucesso!");
    }

    protected Automovel findById(Long id) {
        Automovel automovel = repository.findById(id).orElseThrow(null);

        if(automovel == null){
            log.warn("Não foi possível remover o automoovel; ID não encontrado: {}", id);
            throw new EntityNotFoundException("Automóvel não encontrado com id: " + id);
        }

        return automovel;
    }
}
