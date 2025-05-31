package com.itau.ms_desafio_itau.controller;

import com.itau.ms_desafio_itau.DTO.AutomovelRequestDTO;
import com.itau.ms_desafio_itau.DTO.AutomovelResponseDTO;
import com.itau.ms_desafio_itau.entities.Automovel;
import com.itau.ms_desafio_itau.service.IAutomovelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Automóveis",
        description = "API de gerenciamento de automoveis"
)
@RestController
@Validated
@RequestMapping("/api")
public class AutomovelController {

    private static final Logger log = LoggerFactory.getLogger(AutomovelController.class);

    private final IAutomovelService service;

    public AutomovelController(IAutomovelService service) {
        this.service = service;
    }

    @Operation(summary = "Criar novo automovel", description = "Registra um novo automovel na base de dados")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Automovel criado", content = @Content(schema = @Schema(implementation = AutomovelResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody AutomovelRequestDTO requestDTO) {
        log.info("Requisição recebida para criação de automóvl");

        service.create(requestDTO);

        log.info("Automóvel criado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Listar todos os automoveis", description = "Retorna todos os automoveis cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de automoveis",
            content = @Content(array = @ArraySchema(schema = @Schema(implementation = Automovel.class))))
    @ApiResponse(responseCode = "204", description = "Nenhum automovel encontrado")
    @GetMapping
    public ResponseEntity<List<AutomovelResponseDTO>> listAll() {
        log.info("Requisição para listar todos os automóveis");

        List<AutomovelResponseDTO> responseList = service.listaAll();

        log.info("Retornando {} automóveis", responseList.size());
        return ResponseEntity.ok(responseList);
    }

    @Operation(summary = "Buscar automovel por ID", description = "Retorna um automovel específico pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Automovel encontrado",
            content = @Content(schema = @Schema(implementation = Automovel.class)))
    @ApiResponse(responseCode = "404", description = "Automovel não encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<AutomovelResponseDTO> findById(@PathVariable Long id) {
        log.info("Requisição para buscar automóvel com id: {}", id);

        AutomovelResponseDTO automovel = service.findById(id);

        log.info("Automóvel encontrado com id: {}", id);
        return ResponseEntity.ok(automovel);
    }
}
