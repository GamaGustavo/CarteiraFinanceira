package br.com.gamagustavo.carteirafinanceira.model.dto;

import br.com.gamagustavo.carteirafinanceira.exception.SenhaInvalidaException;
import br.com.gamagustavo.carteirafinanceira.model.entidade.Usuario;

public record Cadastro(String nome, String email, String senha, String confirmaSenha) {

    public void validaSenha() {
        if (senha == null || senha.isBlank()) {
            throw new SenhaInvalidaException("O campo de senha é obrigatório");
        }
        if (confirmaSenha == null || confirmaSenha.isBlank()) {
            throw new SenhaInvalidaException("O campo de confirmação de senha é obrigatório");
        }
        if (!senha.equals(confirmaSenha)) {
            throw new SenhaInvalidaException("A confirmação de senha não confere");
        }
    }

    public Usuario toUsuario() {
        validaSenha();
        return new Usuario(null, nome, email, senha);

    }
}