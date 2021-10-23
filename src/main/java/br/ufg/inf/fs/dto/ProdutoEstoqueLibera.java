package br.ufg.inf.fs.dto;

import java.util.Optional;

import br.ufg.inf.fs.entities.Produto;
import br.ufg.inf.fs.entities.ProdutoEstoque;
import br.ufg.inf.fs.repositories.ProdutoRepository;

public class ProdutoEstoqueLibera {

	private Integer produtoId;
	private Integer quantidade;
	private Boolean isCompra;
	
	public ProdutoEstoqueLibera(Integer produtoId, Integer quantidade, Boolean isCompra) {
		this.produtoId = produtoId;
		this.quantidade = quantidade;
		this.isCompra = isCompra;
	}

	public Integer getProduto() {
		return produtoId;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public Boolean getIsCompra() {
		return isCompra;
	}

	public ProdutoEstoque toModel(ProdutoRepository repository) {
		Optional<Produto> possivelProduto = repository.findById(produtoId);
		return new ProdutoEstoque(possivelProduto.get(), quantidade, 0);
	}
}
