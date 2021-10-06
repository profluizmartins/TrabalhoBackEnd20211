package br.ufg.inf.fs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_produto_estoque")
public class ProdutoEstoque  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto_estoque")
	private Integer idProdutoEstoque;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	@Column(name="id_quantidade_estoque")
	private Integer qtdEstoque;
	
	@Column(name="id_quantidade_reservada")
	private Integer qtdReservada;

	public ProdutoEstoque() {
		super();
	}

	public ProdutoEstoque(Integer idProdutoEstoque, Produto produto, Integer qtdEstoque, Integer qtdReservada) {
		super();
		this.idProdutoEstoque = idProdutoEstoque;
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

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Integer getQtdReservada() {
		return qtdReservada;
	}

	public void setQtdReservada(Integer qtdReservada) {
		this.qtdReservada = qtdReservada;
	}
	

}
