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
import br.ufg.inf.fs.business.ProdutoBusiness;
import br.ufg.inf.fs.entities.Produto;
import br.ufg.inf.fs.exceptions.ProdutoException;

@CrossOrigin
@RestController
@RequestMapping(value="produtos")
public class ProdutoCtrl {

	@Autowired
	private ProdutoBusiness business;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAll(){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		List<Produto> list = new ArrayList<Produto>();
		try {
			list = business.findAll();
			if(list.size() == 0) {
				headers.add("message", Messages.get("0500"));
			}
		}catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0501"));
		}
		return new ResponseEntity<List<Produto>>(list, headers, status);
	}
	
	@GetMapping("/paginator")
	public ResponseEntity<Page<Produto>> paginator(Pageable pageable){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		Page<Produto> list = null;
		try {
			list = business.paginator(pageable);
			if(list.getSize() == 0) {
				headers.add("message", Messages.get("0500"));
			}
		}catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0501"));
		}
		return new ResponseEntity<Page<Produto>>(list, headers, status);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Integer id){
		Produto retorno = new Produto();
		
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		try {
			retorno = business.findById(id);
			if(retorno == null) {
				headers.add("message", Messages.get("0500"));
			}
		}catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0501"));
		}
		return new ResponseEntity<Produto>(retorno, headers, status);
	}
	
	@PostMapping
	public ResponseEntity<Produto> insert(@RequestBody Produto produto){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.CREATED;
		
		try {
			produto = business.insert(produto);
			headers.add("message", Messages.get("0502"));
		} catch (ProdutoException e) {
			headers.add("message", Messages.get(e.getMessage()));
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			headers.add("message", Messages.get("0503"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Produto>(produto, headers, status);
	}
	
	@PutMapping
	public ResponseEntity<Produto> update(@RequestBody Produto produto){
		
		
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		
		try {
			produto = business.update(produto);
			headers.add("message", Messages.get("0504"));
		} catch (ProdutoException e) {
			headers.add("message", Messages.get(e.getMessage()));
			status = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			headers.add("message", Messages.get("0505"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Produto>(produto, headers, status);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.NO_CONTENT;
		
		try {
			business.delete(id);
			headers.add("message", Messages.get("0506"));
		} catch (Exception e) {
			headers.add("message", Messages.get("0507"));
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Void>(headers, status);
	}
	
	@GetMapping("/name/{str}")
	public ResponseEntity<List<Produto>> findByDescProd(@PathVariable String str){
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		List<Produto> list = new ArrayList<Produto>();
		try {
			list = business.findName(str);
			if(list.size() == 0) {
				headers.add("message", Messages.get("0500"));
			}
		}catch (Exception e) {
			status = HttpStatus.BAD_REQUEST;
			headers.add("message", Messages.get("0501"));
		}
		return new ResponseEntity<List<Produto>>(list, headers, status);

	}
}
