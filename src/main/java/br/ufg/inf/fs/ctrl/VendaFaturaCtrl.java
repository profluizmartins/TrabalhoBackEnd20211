package br.ufg.inf.fs.ctrl;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.VendaFaturaBusiness;
import br.ufg.inf.fs.entities.Venda;
import br.ufg.inf.fs.entities.VendaFatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "faturas")
public class VendaFaturaCtrl {
    @Autowired
    VendaFaturaBusiness business;

    @GetMapping("/pagas")
    public ResponseEntity<List<VendaFatura>> findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<VendaFatura> retorno = new ArrayList<VendaFatura>();
        try {
            retorno = business.getFaturasPagas();
            if (retorno.size() == 0) {
                headers.add("message", Messages.get("1000"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<List<VendaFatura>>(retorno, headers, status);
    }

    @GetMapping("/pagas/paginado")
    public ResponseEntity<Page<VendaFatura>> findAll(Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<VendaFatura> retorno = null;
        try {
            retorno = business.getFaturasPagas(pageable);
            if (retorno.getTotalElements() == 0) {
                headers.add("message", Messages.get("1000"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<Page<VendaFatura>>(retorno, headers, status);
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<VendaFatura>> findAllPendentes() {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<VendaFatura> retorno = new ArrayList<VendaFatura>();
        try {
            retorno = business.getFaturasPendentes();
            if (retorno.size() == 0) {
                headers.add("message", Messages.get("1000"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<List<VendaFatura>>(retorno, headers, status);
    }

    @GetMapping("/pendentes/paginado")
    public ResponseEntity<Page<VendaFatura>> findAllPendentes(Pageable pageable) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<VendaFatura> retorno = null;
        try {
            retorno = business.getFaturasPendentes(pageable);
            if (retorno.getTotalElements() == 0) {
                headers.add("message", Messages.get("1000"));
            }
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<Page<VendaFatura>>(retorno, headers, status);
    }

    @PostMapping
    public ResponseEntity<Void> createVendaFatura(@RequestBody Venda v) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        try {
            this.business.createVendaFatura(v);
        } catch (Exception e) {
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<Void>(headers, status);

    }
}
