package br.com.gamagustavo.carteirafinanceira.service;

import br.com.gamagustavo.carteirafinanceira.model.entidade.Carteira;
import br.com.gamagustavo.carteirafinanceira.model.entidade.Historico;
import br.com.gamagustavo.carteirafinanceira.model.Transacao;
import br.com.gamagustavo.carteirafinanceira.repository.HistoricoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HistoricoService {
    private final HistoricoRepository historicoRepository;
    public HistoricoService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    public void registrarHistorico(Carteira carteira, Transacao transacao, BigDecimal valor) {
        Historico historico = new Historico();
        historico.setCarteira(carteira);
        historico.setTransacao(transacao);
        historico.setValor(valor);
        historicoRepository.save(historico);
    }

    public List<Historico> historicoDoUsuario(Long idUsuario) {
       return historicoRepository.historicoDoUsuario(idUsuario);
    }
}
