package br.com.gamagustavo.carteirafinanceira;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Deposito {
    private Long idUsuario;
    private BigDecimal valor;
}
