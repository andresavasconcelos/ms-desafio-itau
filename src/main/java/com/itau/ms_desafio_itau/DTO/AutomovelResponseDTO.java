package com.itau.ms_desafio_itau.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AutomovelResponseDTO(
        Long id,
        String marca,
        String nome,
        BigDecimal preco,
        LocalDateTime date
) {}
