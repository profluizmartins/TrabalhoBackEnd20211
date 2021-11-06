package br.ufg.inf.fs.ctrl;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.ufg.inf.fs.business.ProdutoEstoqueBusiness;
import br.ufg.inf.fs.dto.ProdutoEstoqueDetail;
import br.ufg.inf.fs.dto.ProdutoEstoqueInsert;
import br.ufg.inf.fs.dto.ProdutoEstoqueLibera;
import br.ufg.inf.fs.dto.response.ProdutoEstoqueResponse;
import br.ufg.inf.fs.entities.Produto;
import br.ufg.inf.fs.entities.ProdutoEstoque;
import br.ufg.inf.fs.enums.Grupo;
import br.ufg.inf.fs.enums.UnidadeMedida;
import br.ufg.inf.fs.repositories.ProdutoEstoqueRepository;
import br.ufg.inf.fs.repositories.ProdutoRepository;
import br.ufg.inf.fs.validations.exceptions.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping(value = "/produtoEstoque")
public class ProdutoEstoqueController {

	@Autowired
	private ProdutoEstoqueBusiness business;

	@Autowired
	private ProdutoEstoqueRepository repository;

	@Autowired
	private ProdutoRepository repositoryTeste;

	@GetMapping
	public ResponseEntity<List<ProdutoEstoqueResponse>> listPageProdutoEstoque() {
		List<ProdutoEstoqueResponse> produtosEstoque = business.listAllProdutoEstoque();
		return ResponseEntity.ok().body(produtosEstoque);
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ProdutoEstoqueResponse>> listPageableProdutoEstoque(
				Pageable pageable, 
				@RequestParam("nmProduto") Optional<String> nmProduto) {

		Page<ProdutoEstoqueResponse> produtosEstoque;

		if (!nmProduto.isEmpty()) {
			produtosEstoque = business.listAllProdutoEstoqueinPageableByNmProduto(pageable, nmProduto.get());
		} else {
			produtosEstoque = business.listAllProdutoEstoqueinPageable(pageable);
		}
		
		return ResponseEntity.ok().body(produtosEstoque);
	}

	@GetMapping("/{idProduto}")
	public ResponseEntity<ProdutoEstoqueDetail> getDetalheEstoque(@PathVariable Integer idProduto) {
		Optional<ProdutoEstoque> possivelProdutoEstoque = repository.findByProdutoIdProduto(idProduto);

		if (possivelProdutoEstoque.isEmpty()) {
			throw new NotFoundException("ProdutoEstoque não encontrado para este ID de produto: " + idProduto);
		}

		return ResponseEntity.ok(new ProdutoEstoqueDetail(possivelProdutoEstoque.get()));
	}

	@PostMapping
	public ResponseEntity<ProdutoEstoque> insert(@RequestBody ProdutoEstoqueInsert dto) {
		//teste, remover depois
		Produto produto = new Produto(null, UnidadeMedida.UNIDADE, Grupo.ALVENARIA, "Cimento", "descrição teste", 15.);
		repositoryTeste.save(produto);
		Produto produto1 = new Produto(null, UnidadeMedida.CAIXA, Grupo.ESQUADRIAS, "Telhado", "descrição teste", 0.90);
		repositoryTeste.save(produto1);
		Produto produto2 = new Produto(null, UnidadeMedida.Kg, Grupo.REVESTIMENTO, "Areia", "descrição teste", 220.);
		repositoryTeste.save(produto2);
		Produto produto3 = new Produto(null, UnidadeMedida.SACO, Grupo.TELHADO, "Lampâda", "descrição teste", 20.99);
		repositoryTeste.save(produto3);
		Produto produto4 = new Produto(null, UnidadeMedida.Kg, Grupo.ESQUADRIAS, "Tomada", "descrição teste", 39.89);
		repositoryTeste.save(produto4);
		Produto produto5 = new Produto(null, UnidadeMedida.UNIDADE, Grupo.LOUCAS, "Tapete", "descrição teste", 10.5);
		repositoryTeste.save(produto5);
		Produto produto6 = new Produto(null, UnidadeMedida.UNIDADE, Grupo.ALVENARIA, "Piso", "descrição teste", 110.55);
		repositoryTeste.save(produto6);
		for(int i = 7; i < 11000; i++) {
			repositoryTeste.save(new Produto(null, UnidadeMedida.UNIDADE, Grupo.ALVENARIA, "Piso", "descrição teste", 110.55));
		}
		//

		Optional<ProdutoEstoque> produtoEstoqueOptional = business.insert(dto);

		ProdutoEstoque retorno = produtoEstoqueOptional.get();

		URI uri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/produtoEstoque/{id}")
                .buildAndExpand(retorno.getIdProdutoEstoque())
                .toUri();

		// return ResponseEntity.created(uri).body(retorno);

		for(int j = 0; j < 11000; j++) {
			ProdutoEstoqueInsert dtoT = new ProdutoEstoqueInsert(dto.getProduto()+j, dto.getQuantidade());
			business.insert(dtoT);
		}

		return ResponseEntity.created(uri).body(retorno);
	}

	@PutMapping("/reservar")
	public ResponseEntity<ProdutoEstoque> reservaEstoque(@RequestBody @Valid ProdutoEstoqueInsert dto) {
		Optional<ProdutoEstoque> produtoEstoqueOptional = business.reservarEstoque(dto);

		return ResponseEntity.ok().body(produtoEstoqueOptional.get());
	}

	// @PutMapping("/alterarReserva")
	// public ResponseEntity<ProdutoEstoque> alterarReservaEstoque(@RequestBody @Valid ProdutoEstoqueInsert dto) {
	// 	Integer test = 5;
	// 	Optional<ProdutoEstoque> produtoEstoqueOptional = business.alterarReservaEstoque(dto, test);

	// 	return ResponseEntity.ok().body(produtoEstoqueOptional.get());
	// }

	@PutMapping("/liberar")
	public ResponseEntity<ProdutoEstoque> liberaEstoque(@RequestBody @Valid ProdutoEstoqueLibera dto) {
		Optional<ProdutoEstoque> produtoEstoqueOptional = business.liberarEstoque(dto);

		return ResponseEntity.ok().body(produtoEstoqueOptional.get());
	}
}
