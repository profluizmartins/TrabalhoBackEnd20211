package br.ufg.inf.fs.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.entities.VendaPagamento;
import br.ufg.inf.fs.repository.VendaPagamentoRepository;

@Service
public class VendaPagamentoBusiness {
    @Autowired
    VendaPagamentoRepository repository;

    public List<VendaPagamento> getPagamentosRealizados() {
        return this.repository.findAll();
    }

    public void createPagamento(VendaPagamento vp) {
        this.repository.save(vp);
    }
}