package br.com.gamagustavo.carteirafinanceira.exception;

public class BaseException extends RuntimeException {
    public BaseException(String mensagem) {
        super(mensagem);
    }
    public BaseException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
