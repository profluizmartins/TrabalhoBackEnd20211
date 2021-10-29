package br.ufg.inf.fs.ctrl;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.PessoaBusiness;
import br.ufg.inf.fs.entities.Pessoa;

import br.ufg.inf.fs.exceptions.PessoaException;
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
@RequestMapping(value = "pessoas")
public class PessoaCtrl {

    @Autowired
    private PessoaBusiness business;

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<Pessoa> retorno = new ArrayList<>();
        try{
            retorno = business.findAll();
            if(retorno.size() == 0){
                headers.add("message", Messages.get("0100"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findByID(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Pessoa retorno = new Pessoa();
        try {
            retorno = business.findByID(id);
            if(retorno == null){
                headers.add("message", Messages.get("0100"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Pessoa>> findByNome(@PathVariable String nome){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<Pessoa> retorno = new ArrayList<>();
        try{
            retorno = business.findByNome(nome);
            if(retorno.size() == 0){
                headers.add("message", Messages.get("0100"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }

    @GetMapping("/endereco/{endereco}")
    public ResponseEntity<List<Pessoa>> findByEndereco(@PathVariable String endereco){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<Pessoa> retorno = new ArrayList<>();
        try{
            retorno = business.findByEndereco(endereco);
            if(retorno.size() == 0){
                headers.add("message", Messages.get("0100"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }

    @PostMapping
    public ResponseEntity<Pessoa> insert(@RequestBody Pessoa pessoa){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;
        try {
            pessoa = business.insert(pessoa);
            headers.add("message", Messages.get("0101"));
        } catch (PessoaException e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get(e.getMessage()));
        } catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            headers.add("message", Messages.get("0102"));
        }
        return new ResponseEntity<>(pessoa, headers, status);
    }

    @PutMapping
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        try {
            pessoa = business.update(pessoa);
            headers.add("message", Messages.get("0103"));
        } catch (PessoaException e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get(e.getMessage()));
        } catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            headers.add("message", Messages.get("0104"));
        }
        return new ResponseEntity<>(pessoa, headers, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> delete(@PathVariable Integer id){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        try {
            business.delete(id);
            headers.add("message", Messages.get("0105"));
        } catch (PessoaException e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get(e.getMessage()));
        }
        catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            headers.add("message", Messages.get("0106"));
        }
        return new ResponseEntity<>(headers, status);

    }

    @GetMapping("/paginator")
    public ResponseEntity<Page<Pessoa>> paginatorFindAll(Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<Pessoa> retorno = null;
        try {
            retorno = business.paginatorFindAll(pageable);
            if(retorno.getSize() == 0){
                headers.add("message", Messages.get("0100"));
            }
        }   catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<>(retorno, headers, status);
    }

}
