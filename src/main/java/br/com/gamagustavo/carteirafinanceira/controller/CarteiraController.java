package br.com.gamagustavo.carteirafinanceira.controller;

import br.com.gamagustavo.carteirafinanceira.exception.ValidacaoException;
import br.com.gamagustavo.carteirafinanceira.service.CarteiraService;
import br.com.gamagustavo.carteirafinanceira.model.dto.Deposito;
import br.com.gamagustavo.carteirafinanceira.model.dto.Saque;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping("/deposito")
    public ResponseEntity<Void> depositar (@RequestBody Deposito deposito) throws BadRequestException {
        if(deposito == null) throw new ValidacaoException("Os campos do deposito são obrigatórios!");
        carteiraService.depositar(deposito.idUsuario(),deposito.valor());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/saque")
    public ResponseEntity<Void> sacar(@RequestBody Saque saque) throws BadRequestException {
        if(saque == null) throw new ValidacaoException("Os campos do saque são obrigatórios!");
        carteiraService.sacar(saque.idUsuario(),saque.valor());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/saldo/{idUsuario}")
    public ResponseEntity<BigDecimal> saldo(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(carteiraService.saldo(idUsuario));
    }
}
