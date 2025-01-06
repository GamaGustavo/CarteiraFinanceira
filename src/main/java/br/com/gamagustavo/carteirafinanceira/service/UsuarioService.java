package br.com.gamagustavo.carteirafinanceira.service;

import br.com.gamagustavo.carteirafinanceira.model.entidade.Usuario;
import br.com.gamagustavo.carteirafinanceira.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class UsuarioService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            return usuarioRepository.findByEmail(username);
        } catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).severe("Falha no Login, usuário não encontrado");
            Logger.getLogger(this.getClass().getName()).severe(e.getMessage());
            throw e;
        }
    }
}
