package br.com.gamagustavo.carteirafinanceira.service;

import br.com.gamagustavo.carteirafinanceira.model.dto.Login;
import br.com.gamagustavo.carteirafinanceira.model.entidade.Usuario;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public String autenticar(Login login) {

        var usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(login.email(), login.senha());
        var authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        var usuario = (Usuario) authentication.getPrincipal();
        return tokenService.gerarToken(usuario);
    }
}
