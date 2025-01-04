package br.com.gamagustavo.carteirafinanceira;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
