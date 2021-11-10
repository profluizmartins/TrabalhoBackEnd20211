package br.ufg.inf.fs.ctrl.dto;

import java.util.List;

import br.ufg.inf.fs.entities.Produto;
import br.ufg.inf.fs.entities.Venda;

public class VendaDto {
	
	private Venda venda;
	private List<Produto> produtos;

	public Venda getVenda() {
		return venda;
	}
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
