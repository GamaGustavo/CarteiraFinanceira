package br.com.gamagustavo.carteirafinanceira.controller;

import br.com.gamagustavo.carteirafinanceira.service.CarteiraService;
import br.com.gamagustavo.carteirafinanceira.model.dto.Deposito;
import br.com.gamagustavo.carteirafinanceira.model.dto.Saque;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    private final CarteiraService carteiraService;

    public CarteiraController(CarteiraService carteiraService) {
        this.carteiraService = carteiraService;
    }

    @PostMapping("/deposito")
    public ResponseEntity<Void> depositar (@RequestBody Deposito deposito) throws BadRequestException {
        if(deposito == null) return ResponseEntity.badRequest().build();
        carteiraService.depositar(deposito.idUsuario(),deposito.valor());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/saque")
    public ResponseEntity<Void> sacar(@RequestBody Saque saque) throws BadRequestException {
        if(saque == null) return ResponseEntity.badRequest().build();
        carteiraService.sacar(saque.idUsuario(),saque.valor());
        return ResponseEntity.ok().build();
    }
}
