package br.ufg.inf.fs.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.VendaFaturaBusiness;
import br.ufg.inf.fs.entities.VendaFatura;

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
}
