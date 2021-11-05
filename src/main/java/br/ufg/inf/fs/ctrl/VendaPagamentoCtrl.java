package br.ufg.inf.fs.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufg.inf.fs.business.VendaPagamentoBusiness;

@CrossOrigin
@RestController
@RequestMapping(value = "pagamentos")
public class VendaPagamentoCtrl {

    @Autowired
    VendaPagamentoBusiness business;

    @GetMapping
    public String findAll() {
        String retorno = "";
        try {
            retorno = business.getRelatorioMensalPagamento();
        } catch (Exception e) {
            System.out.println("deu erro garaio");
            retorno = "deu erro garaio";
        }
        return retorno;
    }
}
