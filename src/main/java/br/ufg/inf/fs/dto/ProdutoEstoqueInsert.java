package br.ufg.inf.fs.dto;

import java.util.Optional;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.ufg.inf.fs.entities.Produto;
import br.ufg.inf.fs.entities.ProdutoEstoque;
import br.ufg.inf.fs.repositories.ProdutoRepository;
import br.ufg.inf.fs.validations.exceptions.NotFoundException;

public class ProdutoEstoqueInsert {

	@NotNull
	private Integer produtoId;
	@NotNull
	@Positive
	private Integer quantidade;
	
	public ProdutoEstoqueInsert(Integer produtoId, Integer quantidade) {
		this.produtoId = produtoId;
		this.quantidade = quantidade;
	}

	public Integer getProduto() {
		return produtoId;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public ProdutoEstoque toModel(ProdutoRepository repository) {
		Optional<Produto> possivelProduto = repository.findById(produtoId);
		if(possivelProduto.isEmpty()) {
			throw new NotFoundException("Produto não encontrado");
		}
		return new ProdutoEstoque(possivelProduto.get(), quantidade, 0);
	}
}
