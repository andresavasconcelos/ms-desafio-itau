package com.itau.ms_desafio_itau.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErroResponse(
        int status,
        String error,
        String message,
        LocalDateTime timestamp,
        List<String> errors
) {
    public ErroResponse(int status, String error, String message) {
        this(status, error, message, LocalDateTime.now(), null);
    }

    public ErroResponse(int status, String error, List<String> errors) {
        this(status, error, null, LocalDateTime.now(), errors);
    }
}
