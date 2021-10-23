package br.ufg.inf.fs.dto;

import java.util.Optional;

import br.ufg.inf.fs.entities.Produto;
import br.ufg.inf.fs.entities.ProdutoEstoque;
import br.ufg.inf.fs.repositories.ProdutoRepository;

public class ProdutoEstoqueInsert {

	private Integer produtoId;
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
		return new ProdutoEstoque(possivelProduto.get(), quantidade, 0);
	}
}
