package br.com.gamagustavo.carteirafinanceira.repository;

import br.com.gamagustavo.carteirafinanceira.model.entidade.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    @Query("select hist from Historico hist join Carteira car on car.id = hist.carteira.id where car.usuario.id = ?1 order by hist.data")
    List<Historico> historicoDoUsuario(Long idUsuario);
}
