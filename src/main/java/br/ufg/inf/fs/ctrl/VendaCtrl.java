package br.ufg.inf.fs.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.VendaBusiness;
import br.ufg.inf.fs.ctrl.dto.VendaDto;
import br.ufg.inf.fs.entities.Venda;
import br.ufg.inf.fs.exceptions.PessoaException;


@CrossOrigin
@RestController
@RequestMapping(value = "vendas")
public class VendaCtrl {

    @Autowired
    private VendaBusiness business;

    @GetMapping
    public ResponseEntity<List<Venda>> findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<Venda> retorno = new ArrayList<>();
        try{
            retorno = business.findAll();
            if(retorno.size() == 0){
                headers.add("message", Messages.get("0900"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venda> findByID(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Venda retorno = new Venda();
        try {
            retorno = business.findByID(id);
            if(retorno == null){
                headers.add("message", Messages.get("0900"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }

    @GetMapping("/comprador/{comprador}")
    public ResponseEntity<List<Venda>> findByComprador(@PathVariable Integer comprador){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<Venda> retorno = new ArrayList<>();
        try{
            retorno = business.findByIDComprador(comprador);
            if(retorno.size() == 0){
                headers.add("message", Messages.get("0900"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }

    @PostMapping
    public ResponseEntity<Venda> insert(@RequestBody VendaDto vendadto){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;
        Venda venda = new Venda();
        try {
        	venda = business.insertUpdate(vendadto);
            headers.add("message", Messages.get("0901"));
        } catch (PessoaException e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get(e.getMessage()));
        } catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            headers.add("message", Messages.get("0902"));
        }
        return new ResponseEntity<>(venda, headers, status);
    }

    @PutMapping
    public ResponseEntity<Venda> update(@RequestBody VendaDto vendadto){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Venda venda = new Venda();
        try {
        	venda = business.insertUpdate(vendadto);
            headers.add("message", Messages.get("0903"));
        } catch (PessoaException e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get(e.getMessage()));
        } catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            headers.add("message", Messages.get("0904"));
        }
        return new ResponseEntity<>(venda, headers, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Venda> delete(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        try {
            business.delete(id);
            headers.add("message", Messages.get("0905"));
        } catch (PessoaException e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get(e.getMessage()));
        }
        catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            headers.add("message", Messages.get("0906"));
        }
        return new ResponseEntity<>(headers, status);

    }

    @GetMapping("/paginator")
    public ResponseEntity<Page<Venda>> paginatorFindAll(Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<Venda> retorno = null;
        try {
            retorno = business.paginatorFindAll(pageable);
            if(retorno.getSize() == 0){
                headers.add("message", Messages.get("0900"));
            }
        }   catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }

}
