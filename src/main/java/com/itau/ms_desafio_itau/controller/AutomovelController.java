package com.itau.ms_desafio_itau.controller;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.service.IAutomovelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class AutomovelController {

    private final IAutomovelService service;

    public AutomovelController(IAutomovelService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AutomovelRequestDTO requestDTO){
        service.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<AutomovelResponseDTO>> listAll(){

        List<AutomovelResponseDTO> responseList = service.listaAll();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutomovelResponseDTO> findById(@PathVariable Long id){

        AutomovelResponseDTO automovel = service.findById(id);
        return ResponseEntity.ok(automovel);
    }
}
