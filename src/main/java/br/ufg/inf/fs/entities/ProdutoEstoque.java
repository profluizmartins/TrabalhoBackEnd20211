package br.ufg.inf.fs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.ufg.inf.fs.validations.exceptions.BadRequestException;

@Entity
@Table(name="tb_produto_estoque")
public class ProdutoEstoque  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto_estoque")
	private Integer idProdutoEstoque;
	
	@OneToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	@Column(name="id_quantidade_estoque")
	private Integer qtdEstoque;
	
	@Column(name="id_quantidade_reservada")
	private Integer qtdReservada;

	public ProdutoEstoque() {
		super();
	}

	public ProdutoEstoque(Produto produto, Integer qtdEstoque, Integer qtdReservada) {
		this.produto = produto;
		this.qtdEstoque = qtdEstoque;
		this.qtdReservada = qtdReservada;
	}
	
	public Integer getIdProdutoEstoque() {
		return idProdutoEstoque;
	}

	public void setIdProdutoEstoque(Integer idProdutoEstoque) {
		this.idProdutoEstoque = idProdutoEstoque;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void adicionarQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque += qtdEstoque;
	}
	
	public void removerQtdEstoque(Integer qtdEstoque) {
		if((this.getQtdEstoque() - qtdEstoque) < 0) {
			throw new BadRequestException("Não é possível retirar esta quantidade do estoque");
		}
		this.qtdEstoque -= qtdEstoque;
	}

	public Integer getQtdReservada() {
		return qtdReservada;
	}

	public void adicionarQtdReservada(Integer qtdReservada) {
		if (this.getQtdReservada() + qtdReservada < 0) {
			this.qtdReservada = 0;
			return;
		}

		this.removerQtdEstoque(qtdReservada);
		
		this.qtdReservada += qtdReservada;
	}
	
	public void removerQtdReservada(Integer qtdReservada, boolean isCompra) {
		if(this.getQtdReservada() - qtdReservada < 0) {
			throw new BadRequestException("Não há quantidade reservada suficiente para remover");
		}
		
		this.qtdReservada -= qtdReservada;
		
		if(isCompra) {
			return;
		}
		
		this.adicionarQtdEstoque(qtdReservada);
	}
	

}
