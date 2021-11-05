package br.ufg.inf.fs.business;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufg.inf.fs.entities.VendaPagamento;
import br.ufg.inf.fs.repository.VendaPagamentoRepository;

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

    public String getRelatorioMensalPagamento() {
        return "Balacobaco";
    }

    public String getPrevisao() {
        return "Previsão";
    }

    public String getAtrasos() {
        return "atrasos";
    }
}