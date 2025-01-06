package br.com.gamagustavo.carteirafinanceira.controller;

import br.com.gamagustavo.carteirafinanceira.model.dto.Login;
import br.com.gamagustavo.carteirafinanceira.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        return ResponseEntity.ok(authService.autenticar(login));
    }
}
