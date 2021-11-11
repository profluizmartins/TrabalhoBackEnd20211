package br.ufg.inf.fs.business;

import br.ufg.inf.fs.entities.VendaPagamento;
import br.ufg.inf.fs.repository.VendaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VendaPagamentoBusiness {
    @Autowired
    VendaPagamentoRepository vendaPagamentoRepository;

    public Page<VendaPagamento> getPagamentosRealizados(Pageable pageable) {
        return this.vendaPagamentoRepository.findAll(pageable);
    }

    public Page<VendaPagamento> getPagamentosPendentes(Pageable pageable) {
        return this.vendaPagamentoRepository.getPagamentosPendentes(pageable);
    }

    public void createPagamento(VendaPagamento vPagamento) {
        // Date today = new Date();

        // if(vPagamento.getDtPagamento().after(today)) {

        // }
        this.vendaPagamentoRepository.save(vPagamento);
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
