package br.com.gamagustavo.carteirafinanceira.repository;

import br.com.gamagustavo.carteirafinanceira.model.entidade.Carteira;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarteiraRepository extends JpaRepository<Carteira, Long> {

    Carteira findOneByUsuario_id(Long usuarioId);

    Optional<Carteira> findByUsuario_id(Long usuarioId);
}
