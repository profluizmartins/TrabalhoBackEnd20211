package br.ufg.inf.fs.ctrl;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ufg.inf.fs.business.ProdutoEstoqueBusiness;
import br.ufg.inf.fs.dto.ProdutoEstoqueInsert;
import br.ufg.inf.fs.dto.ProdutoEstoqueLibera;
import br.ufg.inf.fs.entities.ProdutoEstoque;

@RestController
@RequestMapping(value = "/produtoEstoque")
public class ProdutoEstoqueController {

	@Autowired
	private ProdutoEstoqueBusiness business;
	
	@PostMapping
	public ResponseEntity<ProdutoEstoque> insert(@RequestBody ProdutoEstoqueInsert dto) {
		Optional<ProdutoEstoque> produtoEstoqueOptional = business.insert(dto);
		
		ProdutoEstoque retorno = produtoEstoqueOptional.get();
		
		URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/produtoEstoque/{id}")
                .buildAndExpand(retorno.getIdProdutoEstoque())
                .toUri(); 
		
		return ResponseEntity.created(uri).body(retorno);
	}
	
	@PutMapping("/reservar")
	public ResponseEntity<ProdutoEstoque> reservaEstoque(@RequestBody ProdutoEstoqueInsert dto) {
		Optional<ProdutoEstoque> produtoEstoqueOptional = business.reservarEstoque(dto);
		
		return ResponseEntity.ok().body(produtoEstoqueOptional.get());
	}
	
	@PutMapping("/liberar")
	public ResponseEntity<ProdutoEstoque> liberaEstoque(@RequestBody ProdutoEstoqueLibera dto) {
		Optional<ProdutoEstoque> produtoEstoqueOptional = business.liberarEstoque(dto);
		
		return ResponseEntity.ok().body(produtoEstoqueOptional.get());
	}
}
