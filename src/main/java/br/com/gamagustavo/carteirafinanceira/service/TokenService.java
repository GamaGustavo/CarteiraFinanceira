package br.com.gamagustavo.carteirafinanceira.service;

import br.com.gamagustavo.carteirafinanceira.model.entidade.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Service
public class TokenService {


    private final String ISSUER;

    private final String SECRET;

    public TokenService(@Value("{spring.application.name}")String issuer,
                        @Value("{app.secret}") String secret) {
        ISSUER = issuer;
        SECRET = secret;
    }

    public String gerarToken(Usuario usuario) {
        return JWT.create().withIssuer(ISSUER)
                .withSubject(usuario.getUsername())
                .withClaim("id", usuario.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(10)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(SECRET));
    }

    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).withIssuer(ISSUER).build().verify(token).getSubject();
    }


}
