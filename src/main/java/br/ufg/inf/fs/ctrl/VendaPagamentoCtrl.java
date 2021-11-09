package br.ufg.inf.fs.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.VendaPagamentoBusiness;
import br.ufg.inf.fs.entities.VendaPagamento;

@CrossOrigin
@RestController
@RequestMapping(value = "pagamentos")
public class VendaPagamentoCtrl {

    @Autowired
    VendaPagamentoBusiness business;

    @GetMapping
    public ResponseEntity<List<VendaPagamento>> findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<VendaPagamento> retorno = new ArrayList<VendaPagamento>();
        try {
            retorno = business.getPagamentosRealizados();
            if (retorno.size() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<List<VendaPagamento>>(retorno, headers, status);
    }

    @GetMapping("mensal/{mesReferencia}")
    public ResponseEntity<List<VendaPagamento>> getRelatorioMensalPagamento(@PathVariable int mesReferencia) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<VendaPagamento> retorno = new ArrayList<VendaPagamento>();
        try {
            retorno = business.getRelatorioMensalPagamento(mesReferencia);
            if (retorno.size() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<List<VendaPagamento>>(retorno, headers, status);
    }

    @GetMapping("mensal/atrasados/{mesReferencia}")
    public ResponseEntity<List<VendaPagamento>> getRelatorioMensalAtrasos(@PathVariable int mesReferencia) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<VendaPagamento> retorno = new ArrayList<VendaPagamento>();
        try {
            retorno = business.getRelatorioMensalAtrasos(mesReferencia);
            if (retorno.size() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<List<VendaPagamento>>(retorno, headers, status);
    }

    @GetMapping("mensal/previsao/{mesReferencia}")
    public ResponseEntity<List<VendaPagamento>> getRelatorioMensalPrevisao(@PathVariable int mesReferencia) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<VendaPagamento> retorno = new ArrayList<VendaPagamento>();
        try {
            retorno = business.getRelatorioMensalPrevisao(mesReferencia);
            if (retorno.size() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<List<VendaPagamento>>(retorno, headers, status);
    }
}
