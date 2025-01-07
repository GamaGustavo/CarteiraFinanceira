package br.com.gamagustavo.carteirafinanceira.model.entidade;

import br.com.gamagustavo.carteirafinanceira.model.Transacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Historico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false , columnDefinition = "date default now()")
    private LocalDate data;

    @Enumerated(EnumType.ORDINAL)
    private Transacao transacao;

    @Column(nullable = false, columnDefinition = "numeric(12, 2)")
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "carteira_id")
    @JsonIgnore
    private Carteira carteira;

    public Historico() {
        data = LocalDate.now();
    }
}
