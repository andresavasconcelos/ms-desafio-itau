package com.itau.ms_desafio_itau.controller;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.service.IAutomovelService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class AutomovelController {

    private static final Logger log = LoggerFactory.getLogger(AutomovelController.class);

    private final IAutomovelService service;

    public AutomovelController(IAutomovelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AutomovelRequestDTO requestDTO) {
        log.info("Requisição recebida para criação de automóvl");

        service.create(requestDTO);

        log.info("Automóvel criado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<AutomovelResponseDTO>> listAll() {
        log.info("Requisição para listar todos os automóveis");

        List<AutomovelResponseDTO> responseList = service.listaAll();

        log.info("Retornando {} automóveis", responseList.size());
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutomovelResponseDTO> findById(@PathVariable Long id) {
        log.info("Requisição para buscar automóvel com id: {}", id);

        AutomovelResponseDTO automovel = service.findById(id);

        log.info("Automóvel encontrado com id: {}", id);
        return ResponseEntity.ok(automovel);
    }
}
