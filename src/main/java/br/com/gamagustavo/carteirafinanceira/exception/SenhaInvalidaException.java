package br.com.gamagustavo.carteirafinanceira.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(String mensage) {
        super(mensage);
    }
}
