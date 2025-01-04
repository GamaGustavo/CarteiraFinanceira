package br.com.gamagustavo.carteirafinanceira;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/historico")
public class HistoricoController {

    private final HistoricoService historicoService;

    public HistoricoController(HistoricoService historicoService) {
        this.historicoService = historicoService;
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<Historico>> historicoDoUsuario(@PathVariable Long usuarioId) {
        if (usuarioId == null || usuarioId <= 0L) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(historicoService.historicoDoUsuario(usuarioId));
    }
}
