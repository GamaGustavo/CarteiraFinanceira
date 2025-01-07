package br.com.gamagustavo.carteirafinanceira.model.dto;

import java.math.BigDecimal;

public record CarteiraDto(
        Long id, BigDecimal valor, UsuarioDto usuarioDto) {}
