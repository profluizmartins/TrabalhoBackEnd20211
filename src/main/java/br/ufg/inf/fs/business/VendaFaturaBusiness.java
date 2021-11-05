package br.ufg.inf.fs.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.entities.Venda;
import br.ufg.inf.fs.entities.VendaFatura;
import br.ufg.inf.fs.enums.TipoPagamento;
import br.ufg.inf.fs.repository.VendaFaturaRepository;

@Service
public class VendaFaturaBusiness {

    @Autowired
    VendaFaturaRepository repository;

    public void createVendaFatura(Venda v) {
        // pegar valor de cada produto da venda depois e a
        // quantidade e fazer somatório
        Double valorPrestacao = 1000.0 / v.getQtdPrestacao();
        Double desconto = 0.0;

        if (v.getTipoPagamento() == TipoPagamento.DINHEIRO || v.getTipoPagamento() == TipoPagamento.CARTAO_DEBITO
                || v.getTipoPagamento() == TipoPagamento.PIX) {
            valorPrestacao = valorPrestacao - (1000.0 * 0.05);
        }

        for (int i = 0; i < v.getQtdPrestacao(); i++) {
            VendaFatura vf = new VendaFatura(null, v, valorPrestacao, false, desconto);

            this.insert((vf));
        }

    }

    public void insert(VendaFatura vf) {
        this.repository.save(vf);
    }

}
