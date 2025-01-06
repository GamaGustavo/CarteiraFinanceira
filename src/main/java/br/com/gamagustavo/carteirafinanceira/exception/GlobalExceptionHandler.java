package br.com.gamagustavo.carteirafinanceira.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ValidacaoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleSenhaInvalidaException(ValidacaoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"error\": " + ex.getMessage() + "}");
    }

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleObjetoNaoEncontradoException(ObjetoNaoEncontradoException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("{\"error\": \"" + ex.getMessage() + "\"}");
    }

    // Exceções genéricas
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handleGenericException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("{\"error\": \"Erro interno no servidor\"}");
    }
}
