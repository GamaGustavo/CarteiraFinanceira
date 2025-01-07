package br.com.gamagustavo.carteirafinanceira.controller;

import br.com.gamagustavo.carteirafinanceira.exception.ValidacaoException;
import br.com.gamagustavo.carteirafinanceira.model.dto.Deposito;
import br.com.gamagustavo.carteirafinanceira.model.dto.Saque;
import br.com.gamagustavo.carteirafinanceira.service.CarteiraService;
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
    public ResponseEntity<Void> depositar(@RequestBody Deposito deposito) throws ValidacaoException {
        if (deposito == null) throw new ValidacaoException("Os campos do deposito são obrigatórios!");
        carteiraService.depositar(deposito.idUsuario(), new BigDecimal(deposito.valor()));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/saque")
    public ResponseEntity<Void> sacar(@RequestBody Saque saque) throws ValidacaoException {
        if (saque == null) throw new ValidacaoException("Os campos do saque são obrigatórios!");
        carteiraService.sacar(saque.idUsuario(), new BigDecimal(saque.valor()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/saldo/{idUsuario}")
    public ResponseEntity<BigDecimal> saldo(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(carteiraService.saldo(idUsuario));
    }
}
