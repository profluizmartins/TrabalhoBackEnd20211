package br.ufg.inf.fs.ctrl;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.VendaPagamentoBusiness;
import br.ufg.inf.fs.dto.CadastroVendaPagamentoDto;
import br.ufg.inf.fs.entities.VendaPagamento;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("pendentes/paginador")
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

    @GetMapping("pendentes")
    public ResponseEntity<List<VendaPagamento>> getPagamentosPendentesAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<VendaPagamento> retorno = null;
        try {
            retorno = business.getPagamentosPendentesAll();
            if (retorno.size() == 0) {
                headers.add("message", Messages.get("1100"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<List<VendaPagamento>>(retorno, headers, status);
    }

    @GetMapping("aprovados/paginador")
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

    @GetMapping("aprovados")
    public ResponseEntity<List<VendaPagamento>> getPagamentosAprovadosAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<VendaPagamento> retorno = null;
        try {
            retorno = business.getPagamentosRealizadosAll();
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
    public ResponseEntity<Void> cadastrarPagamento(@PathVariable Integer idVendaFatura, @RequestBody CadastroVendaPagamentoDto dto) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        VendaPagamento retorno = null;
        try {
            retorno = business.cadastrarPagamento(idVendaFatura, dto);
            if (retorno == null) {
                status = HttpStatus.NOT_FOUND;
                headers.add("message", Messages.get("1102"));
            }
        } catch (Exception e) {
            System.out.println(e);
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<Void>(headers, status);
    }
}
