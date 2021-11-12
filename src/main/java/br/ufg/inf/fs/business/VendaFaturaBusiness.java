package br.ufg.inf.fs.business;

import br.ufg.inf.fs.entities.Venda;
import br.ufg.inf.fs.entities.VendaFatura;
import br.ufg.inf.fs.enums.TipoPagamento;
import br.ufg.inf.fs.repository.VendaFaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class VendaFaturaBusiness {

    @Autowired
    VendaFaturaRepository repository;

    public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }

    public Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
          .atZone(ZoneId.systemDefault())
          .toInstant());
    }

    public void createVendaFatura(Venda v) {
        // pegar valor de cada produto da venda depois e a
        // quantidade e fazer somatório        
        Double valorPrestacao = 1000.0 / v.getQtdPrestacao();
        Double desconto = 0.0;
        Date today = new Date();        
        

        if (v.getTipoPagamento() == TipoPagamento.DINHEIRO || v.getTipoPagamento() == TipoPagamento.CARTAO_DEBITO
                || v.getTipoPagamento() == TipoPagamento.PIX) {
            valorPrestacao = valorPrestacao - (1000.0 * 0.05);
        }

        for (int i = 1; i <= v.getQtdPrestacao(); i++) {

            
            VendaFatura vf = new VendaFatura(null, v, valorPrestacao, false, desconto, convertToDateViaInstant(convertToLocalDateViaInstant(today).plusMonths(i)));

            this.insert((vf));
        }

    }

    public void insert(VendaFatura vf) {
        this.repository.save(vf);
    }

    public Page<VendaFatura> paginatorFindAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<VendaFatura> getFaturasPagas() {
        return this.repository.findFaturasPagas();
    }

    public Page<VendaFatura> getFaturasPagas(Pageable pageable) {
        return this.repository.findFaturasPagas(pageable);
    }

    public List<VendaFatura> getFaturasPendentes() {
        return this.repository.findFaturasPendentes();
    }

    public Page<VendaFatura> getFaturasPendentes(Pageable pageable) {
        return this.repository.findFaturasPendentes(pageable);
    }
}
