package br.com.gamagustavo.carteirafinanceira.controller;

import br.com.gamagustavo.carteirafinanceira.model.dto.Cadastro;
import br.com.gamagustavo.carteirafinanceira.model.dto.CarteiraDto;
import br.com.gamagustavo.carteirafinanceira.model.entidade.Carteira;
import br.com.gamagustavo.carteirafinanceira.service.CarteiraService;
import br.com.gamagustavo.carteirafinanceira.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final CarteiraService carteiraService;

    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder, CarteiraService carteiraService) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.carteiraService = carteiraService;
    }

    @PostMapping
    public ResponseEntity<CarteiraDto> cadastrarUsuario(@RequestBody Cadastro cadastroUsuario) {
        cadastroUsuario.validarDados();
        var usuario = cadastroUsuario.toUsuario();
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        var usuarioCadastrado = usuarioService.cadastrar(usuario);
        var carteira = carteiraService.criarCarteira(usuarioCadastrado);
        return ResponseEntity.ok(carteira);
    }
}
