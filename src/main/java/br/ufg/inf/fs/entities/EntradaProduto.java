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
@Table(name="tb_entrada_produto")
public class EntradaProduto  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_entrada_produto")
	private Integer idEntradaProduto;
	
	
	@ManyToOne
	@JoinColumn(name="entrada_id")
	private Entrada entrada;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	@Column(name="preco_entrada_produto")
	private Double precoEntradaProduto;
	
	private Integer quantidade;

	public EntradaProduto() {
		super();
	}

	public EntradaProduto(Integer idEntradaProduto, Entrada entrada, Produto produto, Double precoEntradaProduto,
			Integer quantidade) {
		super();
		this.idEntradaProduto = idEntradaProduto;
		this.entrada = entrada;
		this.produto = produto;
		this.precoEntradaProduto = precoEntradaProduto;
		this.quantidade = quantidade;
	}

	public Integer getIdEntradaProduto() {
		return idEntradaProduto;
	}

	public void setIdEntradaProduto(Integer idEntradaProduto) {
		this.idEntradaProduto = idEntradaProduto;
	}

	public Entrada getEntrada() {
		return entrada;
	}

	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getPrecoEntradaProduto() {
		return precoEntradaProduto;
	}

	public void setPrecoEntradaProduto(Double precoEntradaProduto) {
		this.precoEntradaProduto = precoEntradaProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
