package br.com.gamagustavo.carteirafinanceira.exception;

public class ValidacaoException extends RuntimeException {
    public ValidacaoException(String mensage) {
        super(mensage);
    }
}
