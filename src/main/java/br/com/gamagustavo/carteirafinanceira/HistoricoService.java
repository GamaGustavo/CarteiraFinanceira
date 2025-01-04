package br.com.gamagustavo.carteirafinanceira;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {
    private final HistoricoRepository historicoRepository;
    public HistoricoService(HistoricoRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    public void registrarHistorico(Carteira carteira, Transacao transacao) {
        Historico historico = new Historico();
        historico.setCarteira(carteira);
        historico.setTransacao(transacao);
        historicoRepository.save(historico);
    }

    public List<Historico> historicoDoUsuario(Long idUsuario) {
       return historicoRepository.historicoDoUsuario(idUsuario);
    }
}
