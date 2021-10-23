package br.ufg.inf.fs.ctrl;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ufg.inf.fs.business.ProdutoEstoqueBusiness;
import br.ufg.inf.fs.entities.ProdutoEstoque;

@RestController
@RequestMapping(value = "/produtoEstoque")
public class ProdutoEstoqueController {

	@Autowired
	private ProdutoEstoqueBusiness business;
	
	@PostMapping
	public ResponseEntity<ProdutoEstoque> insert(@RequestBody ProdutoEstoque produtoEstoque) {
		Optional<ProdutoEstoque> produtoEstoqueOptional = business.insert(produtoEstoque);
		
		if(produtoEstoqueOptional.isEmpty()) {
			// return ResponseEntity.badRequest();
		}
		
		ProdutoEstoque retorno = produtoEstoqueOptional.get();
		
		URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/produtoEstoque/{id}")
                .buildAndExpand(produtoEstoque.getIdProdutoEstoque())
                .toUri(); 
		
		return ResponseEntity.created(uri).body(retorno);
	}
}
