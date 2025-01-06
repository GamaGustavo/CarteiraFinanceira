package br.com.gamagustavo.carteirafinanceira.service;

import br.com.gamagustavo.carteirafinanceira.exception.ObjetoNaoEncontradoException;
import br.com.gamagustavo.carteirafinanceira.exception.ValidacaoException;
import br.com.gamagustavo.carteirafinanceira.model.Transacao;
import br.com.gamagustavo.carteirafinanceira.model.entidade.Carteira;
import br.com.gamagustavo.carteirafinanceira.repository.CarteiraRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CarteiraService {
    private final CarteiraRepository carteiraRepository;
    private final HistoricoService historicoService;

    public CarteiraService(CarteiraRepository carteiraRepository, HistoricoService historicoService) {
        this.carteiraRepository = carteiraRepository;
        this.historicoService = historicoService;
    }

    public BigDecimal saldo(Long usuarioId) throws ObjetoNaoEncontradoException {
        Optional<Carteira> carteira = carteiraRepository.findByUsuario_id(usuarioId);
        return carteira.orElseThrow(() -> new ObjetoNaoEncontradoException("Saldo não encontrado")).getValor();
    }

    public void depositar(Long usuarioId, BigDecimal valor) throws ValidacaoException {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)
            throw new ValidacaoException("Valor do deposito deve ser superior a 0!");
        if (usuarioId == null) throw new ValidacaoException("Usuário invalido");
        Optional<Carteira> carteira = carteiraRepository.findByUsuario_id(usuarioId);
        var carteiraFinal = carteira.orElseThrow(() -> new ValidacaoException("Usuário inválido"));

        carteiraFinal.setValor(carteiraFinal.getValor().add(valor));
        carteiraRepository.save(carteiraFinal);
        historicoService.registrarHistorico(carteiraFinal, Transacao.DEPOSITO);
    }

    public void sacar(Long usuarioId, BigDecimal valor) throws ValidacaoException {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)
            throw new ValidacaoException("Valor do saque não pode ser negativo");
        Optional<Carteira> carteira = carteiraRepository.findByUsuario_id(usuarioId);
        var carteiraFinal = carteira.orElseThrow(() -> new ValidacaoException("Usuário invalido"));

        if (carteiraFinal.getValor().compareTo(valor) < 0) {
            throw new RuntimeException("Não existe saldo o suficiente para o saque");
        }
        carteiraFinal.setValor(carteiraFinal.getValor().subtract(valor));

        carteiraRepository.save(carteiraFinal);
        historicoService.registrarHistorico(carteiraFinal, Transacao.SAQUE);
    }


}
