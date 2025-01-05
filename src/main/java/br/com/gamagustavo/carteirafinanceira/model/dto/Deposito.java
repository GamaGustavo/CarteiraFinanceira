package br.com.gamagustavo.carteirafinanceira.model.dto;

import java.math.BigDecimal;

public record Deposito(
        Long idUsuario,
        BigDecimal valor
){}
