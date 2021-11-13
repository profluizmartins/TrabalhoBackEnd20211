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

import java.util.Date;
import java.util.List;

@Service
public class VendaPagamentoBusiness {
    @Autowired
    VendaPagamentoRepository vendaPagamentoRepository;
    @Autowired
    VendaFaturaRepository vendaFaturaRepository;

    public Page<VendaPagamento> getPagamentosRealizados(Pageable pageable) {
        return this.vendaPagamentoRepository.findAll(pageable);
    }

    public List<VendaPagamento> getPagamentosRealizadosAll() {
        return this.vendaPagamentoRepository.findAll();
    }

    public Page<VendaPagamento> getPagamentosPendentes(Pageable pageable) {
        return this.vendaPagamentoRepository.getPagamentosPendentes(pageable);
    }

    public List<VendaPagamento> getPagamentosPendentesAll() {
        return this.vendaPagamentoRepository.getPagamentosPendentesAll();
    }

    public VendaPagamento cadastrarPagamento(Integer idVenda, CadastroVendaPagamentoDto dto) {

        VendaFatura vendaFatura = this.vendaFaturaRepository.getById(idVenda);

        if (vendaFatura.getValorVenda() == null) {
            return null;
        }

        VendaPagamento vendaPagamento = new VendaPagamento(null, dto.getValor(), dto.getDate(), vendaFatura);
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
}
