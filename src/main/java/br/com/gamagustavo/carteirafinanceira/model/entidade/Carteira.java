package br.com.gamagustavo.carteirafinanceira.model.entidade;

import br.com.gamagustavo.carteirafinanceira.model.dto.CarteiraDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Carteira {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "numeric(12, 2) default 0.00")
    private BigDecimal valor;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Carteira() {
        super();
        this.valor = BigDecimal.ZERO;
    }

    public CarteiraDto toDto() {
        return new CarteiraDto(this.id , this.valor, this.usuario.toDTO());
    }
}
