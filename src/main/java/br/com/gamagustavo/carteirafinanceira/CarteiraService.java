package br.com.gamagustavo.carteirafinanceira;

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

    public BigDecimal saldo(Long usuarioId) throws BadRequestException {
        Optional<Carteira> carteira = carteiraRepository.findByUsuario_id(usuarioId);
        return carteira.orElseThrow(BadRequestException::new).getValor();
    }

    public void depositar(Long usuarioId, BigDecimal valor) throws BadRequestException {
        Optional<Carteira> carteira = carteiraRepository.findByUsuario_id(usuarioId);
        carteira.ifPresentOrElse(
                carteira1 -> {
                    carteira1.setValor(carteira1.getValor().add(valor));
                    carteiraRepository.save(carteira1);
                    historicoService.registrarHistorico(carteira1,Transacao.DEPOSITO);
                },
                BadRequestException::new
        );
    }

    public void sacar(Long usuarioId, BigDecimal valor) throws BadRequestException {
        if (valor.compareTo(new BigDecimal(0)) < 0) {
            throw new BadRequestException("Valor do saque não pode ser negativo");
        }
        Optional<Carteira> carteira = carteiraRepository.findByUsuario_id(usuarioId);
        var carteiraFinal = carteira.orElseThrow(() -> new BadRequestException("Usuário invalido"));

        if (carteiraFinal.getValor().compareTo(valor) < 0) {
            throw new RuntimeException("Não existe saldo o suficiente para o saque");
        }
        carteiraFinal.setValor(carteiraFinal.getValor().subtract(valor));

        carteiraRepository.save(carteiraFinal);
        historicoService.registrarHistorico(carteiraFinal,Transacao.SAQUE);
    }
}
