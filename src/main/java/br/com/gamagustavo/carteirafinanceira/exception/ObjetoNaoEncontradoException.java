package br.com.gamagustavo.carteirafinanceira.exception;

public class ObjetoNaoEncontradoException extends BaseException {

    public ObjetoNaoEncontradoException(String message) {
        super(message);
    }
    public ObjetoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
