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
import br.ufg.inf.fs.business.PessoaJuridicaBusiness;
import br.ufg.inf.fs.entities.PessoaJuridica;
import br.ufg.inf.fs.exceptions.PessoaJuridicaException;

@CrossOrigin
@RestController
@RequestMapping(value = "pessoasJuridicas")
public class PessoaJuridicaCtrl {
	
    @Autowired
    private PessoaJuridicaBusiness business;
    
    @GetMapping
    public ResponseEntity<List<PessoaJuridica>> findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<PessoaJuridica> retorno = new ArrayList<>();
        try{
            retorno = business.findAll();
            if(retorno.size() == 0){
                headers.add("message", Messages.get("0200"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PessoaJuridica> findByID(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        PessoaJuridica retorno = new PessoaJuridica();
        try {
            retorno = business.findByID(id);
            if(retorno == null){
                headers.add("message", Messages.get("0200"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }
    
    @GetMapping("/atividade/{atividade}")
    public ResponseEntity<List<PessoaJuridica>> findByAtividade(@PathVariable String atividade){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<PessoaJuridica> retorno = new ArrayList<>();
        try{
            retorno = business.findByAtividade(atividade);
            if(retorno.size() == 0){
                headers.add("message", Messages.get("0200"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }
    
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<List<PessoaJuridica>> findByCNPJ(@PathVariable String cnpj){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<PessoaJuridica> retorno = new ArrayList<>();
        try{
            retorno = business.findByCNPJ(cnpj);
            if(retorno.size() == 0){
                headers.add("message", Messages.get("0200"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }
    
    @PostMapping
    public ResponseEntity<PessoaJuridica> insert(@RequestBody PessoaJuridica pessoaJuridica){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;
        try {
            pessoaJuridica = business.insert(pessoaJuridica);
            headers.add("message", Messages.get("0201"));
        } catch (PessoaJuridicaException e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get(e.getMessage()));
        } catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            headers.add("message", Messages.get("0202"));
        }
        return new ResponseEntity<>(pessoaJuridica, headers, status);
    }
    
    @PutMapping
    public ResponseEntity<PessoaJuridica> update(@RequestBody PessoaJuridica pessoaJuridica){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        try {
            pessoaJuridica = business.update(pessoaJuridica);
            headers.add("message", Messages.get("0203"));
        } catch (PessoaJuridicaException e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get(e.getMessage()));
        } catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            headers.add("message", Messages.get("0204"));
        }
        return new ResponseEntity<>(pessoaJuridica, headers, status);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaJuridica> delete(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        try {
            business.delete(id);
            headers.add("message", Messages.get("0205"));
        } catch (PessoaJuridicaException e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get(e.getMessage()));
        }
        catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            headers.add("message", Messages.get("0206"));
        }
        return new ResponseEntity<>(headers, status);

    }
    
    @GetMapping("/paginator")
    public ResponseEntity<Page<PessoaJuridica>> paginatorFindAll(Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<PessoaJuridica> retorno = null;
        try {
            retorno = business.paginatorFindAll(pageable);
            if(retorno.getSize() == 0){
                headers.add("message", Messages.get("0200"));
            }
        }   catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }
	
}
