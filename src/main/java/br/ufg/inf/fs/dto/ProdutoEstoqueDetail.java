package br.ufg.inf.fs.dto;

import br.ufg.inf.fs.entities.Produto;
import br.ufg.inf.fs.entities.ProdutoEstoque;

public class ProdutoEstoqueDetail {

	private Integer idProdutoEstoque;
	private Produto produto;
	private Integer qtdEstoque;
	
	public ProdutoEstoqueDetail(Integer idProdutoEstoque, Produto produto, Integer qtdEstoque) {
		this.idProdutoEstoque = idProdutoEstoque;
		this.produto = produto;
		this.qtdEstoque = qtdEstoque;
	}
	
	public ProdutoEstoqueDetail(ProdutoEstoque entity) {
		this.idProdutoEstoque = entity.getIdProdutoEstoque();
		this.produto = entity.getProduto();
		this.qtdEstoque = entity.getQtdEstoque();
	}

	public Integer getIdProdutoEstoque() {
		return idProdutoEstoque;
	}

	public Produto getProduto() {
		return produto;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}
}
