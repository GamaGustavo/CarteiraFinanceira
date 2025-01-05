package br.com.gamagustavo.carteirafinanceira.model.dto;

import java.math.BigDecimal;


public record Saque(
        Long idUsuario,
        BigDecimal valor
) {}
