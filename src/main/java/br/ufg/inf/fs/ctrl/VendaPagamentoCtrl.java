package br.ufg.inf.fs.ctrl;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.VendaPagamentoBusiness;
import br.ufg.inf.fs.entities.VendaPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "pagamentos")
public class VendaPagamentoCtrl {

    @Autowired
    VendaPagamentoBusiness business;

    @GetMapping
    public ResponseEntity<Page<VendaPagamento>> findAll(Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<VendaPagamento> retorno = null;
        try {
            retorno = business.getPagamentosRealizados(pageable);
            if (retorno.getTotalElements() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<Page<VendaPagamento>>(retorno, headers, status);
    }


    @GetMapping("pendentes")
    public ResponseEntity<Page<VendaPagamento>> getPagamentosPendentes(Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<VendaPagamento> retorno = null;
        try {
            retorno = business.getPagamentosPendentes(pageable);
            if (retorno.getTotalElements() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<Page<VendaPagamento>>(retorno, headers, status);
    }

    @GetMapping("aprovados")
    public ResponseEntity<Page<VendaPagamento>> getPagamentosAprovados(Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<VendaPagamento> retorno = null;
        try {
            retorno = business.getPagamentosRealizados(pageable);
            if (retorno.getTotalElements() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<Page<VendaPagamento>>(retorno, headers, status);
    }

    @GetMapping("mensal/{mesReferencia}")
    public ResponseEntity<Page<VendaPagamento>> getRelatorioMensalPagamento(@PathVariable int mesReferencia, Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<VendaPagamento> retorno = null;
        try {
            retorno = business.getRelatorioMensalPagamento(mesReferencia, pageable);
            if (retorno.getTotalElements() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<Page<VendaPagamento>>(retorno, headers, status);
    }

    @GetMapping("mensal/atrasados/{mesReferencia}")
    public ResponseEntity<Page<VendaPagamento>> getRelatorioMensalAtrasos(@PathVariable int mesReferencia, Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<VendaPagamento> retorno = null;
        try {
            retorno = business.getRelatorioMensalAtrasos(mesReferencia, pageable);
            if (retorno.getTotalElements() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<Page<VendaPagamento>>(retorno, headers, status);
    }

    @GetMapping("mensal/previsao/{mesReferencia}")
    public ResponseEntity<Page<VendaPagamento>> getRelatorioMensalPrevisao(@PathVariable int mesReferencia, Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<VendaPagamento> retorno = null;
        try {
            retorno = business.getRelatorioMensalPrevisao(mesReferencia, pageable);
            if (retorno.getTotalElements() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<Page<VendaPagamento>>(retorno, headers, status);
    }

    @PostMapping("cadastrarPagamento/{idVendaFatura}")
    public ResponseEntity<VendaPagamento> cadastrarPagamento(@PathVariable Integer idVendaFatura, Date date, Double valor) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        VendaPagamento retorno = null;
        try {
            retorno = business.cadastrarPagamento(idVendaFatura, date, valor);
            if (retorno == null) {
                headers.add("message", Messages.get("1102"));
            }
        } catch (Exception e) {
            System.out.println(e);
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<VendaPagamento>(retorno, headers, status);
    }
}
