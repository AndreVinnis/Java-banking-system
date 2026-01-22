package com.andre.projetobanco.DTO.Infra;

public record StandardError(
        Integer status,
        String message,
        Long timestamp
) {}
