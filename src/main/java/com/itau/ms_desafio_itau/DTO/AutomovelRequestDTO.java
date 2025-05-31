package com.itau.ms_desafio_itau.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record AutomovelRequestDTO(

    @NotBlank(message = "A marca do automóvel é obrigatório")
    @Size(min = 2, max = 100, message = "A marca deve ter entre 2 e 100 caracteres")
    String marca,

    @NotBlank(message = "O nome do automóvel é obrigatório")
    @Size(min = 2, max = 100, message = "O nome do automóvel deve ter entre 2 e 100 caracteres")
    String nome,

    @NotBlank(message = "O preço do automóvel é obrigatório")
    BigDecimal preco
)
{}
