package br.ufg.inf.fs.business;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.fs.dto.ProdutoEstoqueInsert;
import br.ufg.inf.fs.dto.ProdutoEstoqueLibera;
import br.ufg.inf.fs.entities.ProdutoEstoque;
import br.ufg.inf.fs.repositories.ProdutoEstoqueRepository;
import br.ufg.inf.fs.repositories.ProdutoRepository;
import br.ufg.inf.fs.validations.exceptions.NotFoundException;

@Service
public class ProdutoEstoqueBusiness {

	@Autowired
	private ProdutoEstoqueRepository repository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public Optional<ProdutoEstoque> insert(ProdutoEstoqueInsert dto) {
		Optional<ProdutoEstoque> possivelProdutoEstoque = repository.findByProdutoIdProduto(dto.getProduto());
		if (possivelProdutoEstoque.isEmpty()) {
			ProdutoEstoque entity = dto.toModel(produtoRepository);
			return Optional.of(repository.save(entity));
		}
		ProdutoEstoque produtoEstoque = possivelProdutoEstoque.get();
		produtoEstoque.adicionarQtdEstoque(dto.getQuantidade());
		return Optional.of(produtoEstoque);
	}
	
	@Transactional
	public Optional<ProdutoEstoque> reservarEstoque(ProdutoEstoqueInsert dto) {
		Optional<ProdutoEstoque> possivelProdutoEstoque = repository.findByProdutoIdProduto(dto.getProduto());
		if (possivelProdutoEstoque.isEmpty()) {
			throw new NotFoundException("ProdutoEstoque não foi encontrado para o Produto informado");
		}
		ProdutoEstoque produtoEstoque = possivelProdutoEstoque.get();
		produtoEstoque.adicionarQtdReservada(dto.getQuantidade());
		return Optional.of(produtoEstoque);
	}
	
	@Transactional
	public Optional<ProdutoEstoque> liberarEstoque(ProdutoEstoqueLibera dto) {
		Optional<ProdutoEstoque> possivelProdutoEstoque = repository.findByProdutoIdProduto(dto.getProduto());
		
		if (possivelProdutoEstoque.isEmpty()) {
			throw new NotFoundException("ProdutoEstoque não foi encontrado para o Produto informado");
		}
		
		ProdutoEstoque produtoEstoque = possivelProdutoEstoque.get();
		
		produtoEstoque.removerQtdReservada(dto.getQuantidade(), dto.getIsCompra());
		return Optional.of(produtoEstoque);
	}
}
