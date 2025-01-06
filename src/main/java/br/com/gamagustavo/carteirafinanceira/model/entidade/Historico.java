package br.com.gamagustavo.carteirafinanceira.model.entidade;

import br.com.gamagustavo.carteirafinanceira.model.Transacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @ManyToOne
    @JoinColumn(name = "carteira_id")
    private Carteira carteira;

    public Historico() {
        data = LocalDate.now();
    }
}
