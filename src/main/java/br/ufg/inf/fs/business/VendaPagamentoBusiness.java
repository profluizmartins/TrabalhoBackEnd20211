package br.ufg.inf.fs.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.entities.VendaPagamento;
import br.ufg.inf.fs.repository.VendaPagamentoRepository;

@Service
public class VendaPagamentoBusiness {
    @Autowired
    VendaPagamentoRepository vendaPagamentoRepository;

    public List<VendaPagamento> getPagamentosRealizados() {
        return this.vendaPagamentoRepository.findAll();
    }

    public List<VendaPagamento> getPagamentosPendentes() {
        return this.vendaPagamentoRepository.getPagamentosPendentes();
    }

    public void createPagamento(VendaPagamento vPagamento) {
        // Date today = new Date();

        // if(vPagamento.getDtPagamento().after(today)) {

        // }
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
