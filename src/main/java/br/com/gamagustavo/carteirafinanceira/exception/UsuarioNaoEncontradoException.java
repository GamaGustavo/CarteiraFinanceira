package br.com.gamagustavo.carteirafinanceira.exception;

public class UsuarioNaoEncontradoException extends BaseException {
    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
