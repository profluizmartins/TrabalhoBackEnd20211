package br.ufg.inf.fs.ctrl;

import br.ufg.inf.fs.Messages;
import br.ufg.inf.fs.business.UsuarioBusiness;
import br.ufg.inf.fs.entities.Usuario;
import br.ufg.inf.fs.exceptions.UsuarioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value="usuarios")
public class UsuarioCtrl {
    @Autowired
    UsuarioBusiness business;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<Usuario> retorno = new ArrayList<Usuario>();
        try {
            retorno = business.findAll();
            if(retorno.size() == 0){
                headers.add("message", Messages.get("0300"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<List<Usuario>>(retorno, headers, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable String id){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Usuario retorno = new Usuario();
        try {
            retorno = business.findById(id);
            if(retorno == null){
                headers.add("message", Messages.get("0300"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }

        return new ResponseEntity<Usuario>(retorno, headers, status);
    }

    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;

        try {
            usuario = business.insert(usuario);
            headers.add("message", Messages.get("0301"));
        } catch (UsuarioException e) {
            headers.add("message", Messages.get(e.getMessage()));
            status = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            headers.add("message", Messages.get("0302"));
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Usuario>(usuario, headers, status);
    }

    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;

        try {
            usuario = business.update(usuario);
            headers.add("message", Messages.get("0303"));
        } catch (UsuarioException e) {
            headers.add("message", Messages.get(e.getMessage()));
            status = HttpStatus.BAD_REQUEST;
        } catch (Exception e) {
            headers.add("message", Messages.get("0304"));
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Usuario>(usuario, headers, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        try {
            business.delete(id);
            headers.add("message", Messages.get("0305"));
        } catch (Exception e) {
            headers.add("message", Messages.get("0306"));
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Void>(headers, status);
    }

    @GetMapping("/paginator")
    public ResponseEntity<Page<Usuario>> paginatorFindAll(Pageable pageable){
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Page<Usuario> retorno = null;
        try {
            retorno = business.paginatorFindAll(pageable);
            if(retorno.getSize() == 0){
                headers.add("message", Messages.get("0300"));
            }
        } catch (Exception e){
            status = HttpStatus.BAD_REQUEST;
            headers.add("message", Messages.get("0002"));
        }
        return new ResponseEntity<Page<Usuario>>(retorno, headers, status);
    }

}
