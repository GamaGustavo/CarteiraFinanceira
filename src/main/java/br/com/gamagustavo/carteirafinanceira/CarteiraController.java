package br.com.gamagustavo.carteirafinanceira;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController("/carteira")
public class CarteiraController {

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping("/deposito")
    public ResponseEntity<Void> depositar (@RequestBody Deposito deposito) throws BadRequestException {
        if(deposito == null) return ResponseEntity.badRequest().build();
        carteiraService.depositar(deposito.getIdUsuario(),deposito.getValor());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/saque")
    public ResponseEntity<Void> sacar(@RequestBody Saque saque) throws BadRequestException {
        if(saque == null) return ResponseEntity.badRequest().build();
        carteiraService.sacar(saque.getIdUsuario(),saque.getValor());
        return ResponseEntity.ok().build();
    }
}
