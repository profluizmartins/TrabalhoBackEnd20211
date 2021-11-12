package br.ufg.inf.fs.business;

import br.ufg.inf.fs.dto.CadastroVendaPagamentoDto;
import br.ufg.inf.fs.entities.VendaFatura;
import br.ufg.inf.fs.entities.VendaPagamento;
import br.ufg.inf.fs.repository.VendaFaturaRepository;
import br.ufg.inf.fs.repository.VendaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class VendaPagamentoBusiness {
    @Autowired
    VendaPagamentoRepository vendaPagamentoRepository;
    @Autowired
    VendaFaturaRepository vendaFaturaRepository;

    public Page<VendaPagamento> getPagamentosRealizados(Pageable pageable) {
        return this.vendaPagamentoRepository.findAll(pageable);
    }

    public Page<VendaPagamento> getPagamentosPendentes(Pageable pageable) {
        return this.vendaPagamentoRepository.getPagamentosPendentes(pageable);
    }

    public VendaPagamento cadastrarPagamento(Integer idVenda, CadastroVendaPagamentoDto dto) {

        VendaFatura vendaFatura = this.vendaFaturaRepository.getById(idVenda);

        if (vendaFatura.getValorVenda() == null) {
            return null;
        }

        Double vencimento = calcularVencimento(dto.getDate(), vendaFatura.getDataVencimento(), dto.getValor());

        VendaPagamento vendaPagamento = new VendaPagamento(null, dto.getValor()+vencimento, dto.getDate(), vendaFatura);
        return this.vendaPagamentoRepository.save(vendaPagamento);

    }

    public Page<VendaPagamento> getRelatorioMensalPagamento(int mesReferencia, Pageable pageable) {
        return this.vendaPagamentoRepository.findVendaPagamentosByMes(mesReferencia, pageable);
    }

    public Page<VendaPagamento> getRelatorioMensalPrevisao(int mesReferencia, Pageable pageable) {
        return this.vendaPagamentoRepository.findPagamentosPrevisaoByMes(mesReferencia, pageable);
    }

    public Page<VendaPagamento> getRelatorioMensalAtrasos(int mesReferencia, Pageable pageable) {
        return this.vendaPagamentoRepository.findPagamentosAtrasadosByMes(mesReferencia, pageable);
    }

    Double calcularVencimento(Date dataPagamento, Date dataFatura, Double valor) {
        Calendar calPagamento = Calendar.getInstance();
        Calendar calFatura = Calendar.getInstance();
        calPagamento.setTime(dataPagamento);
        calFatura.setTime(dataFatura);
        long dias = TimeUnit.MILLISECONDS.toDays(Math.abs(calPagamento.getTimeInMillis() - calFatura.getTimeInMillis()));
        double valorVencimento = 0.0;
        if (dias > 0) {
            valorVencimento = valor + (valor * 0.1);
        } else {
            return 0.0;
        }

        for (int dia = 1; dia < dias; dia++) {
            valorVencimento = valorVencimento + (valorVencimento * 0.005);
        }
        return valorVencimento - valor;
    }
}
