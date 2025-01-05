package br.com.gamagustavo.carteirafinanceira.service;

import br.com.gamagustavo.carteirafinanceira.model.entidade.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {
    public String gerarToken(Usuario usuario) {
        return JWT.create().withIssuer("Carteira")
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(10)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(usuario.getSenha()));
    }
}
