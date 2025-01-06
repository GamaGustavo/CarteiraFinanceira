package br.com.gamagustavo.carteirafinanceira.exception;

import br.com.gamagustavo.carteirafinanceira.model.dto.Erro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Erro> handleUsuarioNaoEncontrado(UsuarioNaoEncontradoException ex) {
        log.error("Usuário não encontrado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Erro("user.not_found", ex.getMessage()));
    }
    
    @ExceptionHandler(ValidacaoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Erro> handleSenhaInvalidaException(ValidacaoException ex) {
        log.error("Falha na validação do objeto: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Erro("request.bad_request", ex.getMessage()));
    }

    @ExceptionHandler(ObjetoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Erro> handleObjetoNaoEncontradoException(ObjetoNaoEncontradoException ex) {
        log.error("Objeto não encontrado: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new Erro("request.bad_request", ex.getMessage()));
    }
}
