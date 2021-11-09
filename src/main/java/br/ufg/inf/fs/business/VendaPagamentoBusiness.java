package br.ufg.inf.fs.business;

import br.ufg.inf.fs.entities.VendaPagamento;
import br.ufg.inf.fs.repository.VendaPagamentoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VendaPagamentoBusiness {
    VendaPagamentoRepository vendaPagamentoRepository;

    public List<VendaPagamento> getPagamentosRealizados() {
        return this.vendaPagamentoRepository.findAll();
    }

    public List<VendaPagamento> getPagamentosPendentes() {
        return this.vendaPagamentoRepository.findAll();
    }

    public void createPagamento(VendaPagamento vPagamento) {
        this.vendaPagamentoRepository.save(vPagamento);
    }

    public List<VendaPagamento> getRelatorioMensalPagamento(int mesReferencia) {
        return this.vendaPagamentoRepository.findVendaPagamentosByMes(mesReferencia);
    }

    public List<VendaPagamento> getRelatorioMensalPrevisao(int mesReferencia) {
        return new ArrayList<>();
    }

    public List<VendaPagamento> getRelatorioMensalAtrasos(int mesReferencia) {
        return this.vendaPagamentoRepository.findPagamentosAtrasadosByMes(mesReferencia);
    }
}
