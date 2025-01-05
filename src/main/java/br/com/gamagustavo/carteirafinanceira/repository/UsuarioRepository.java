package br.com.gamagustavo.carteirafinanceira.repository;

import br.com.gamagustavo.carteirafinanceira.model.entidade.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
