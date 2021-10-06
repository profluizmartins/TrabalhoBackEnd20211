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
@Table(name="tb_venda_produto")
public class VendaProduto  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_venda_produto")
	private Integer idVendaProduto;
	
	@ManyToOne
	@JoinColumn(name="venda_id")
	private Venda venda;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
	
	@Column(name="preco_venda_produto")
	private Double precoVendaProduto;
	
	private Integer quantidade;

	public VendaProduto() {
		super();
	}

	public VendaProduto(Integer idVendaProduto, Venda venda, Produto produto, Double precoVendaProduto,
			Integer quantidade) {
		super();
		this.idVendaProduto = idVendaProduto;
		this.venda = venda;
		this.produto = produto;
		this.precoVendaProduto = precoVendaProduto;
		this.quantidade = quantidade;
	}

	public Integer getIdVendaProduto() {
		return idVendaProduto;
	}

	public void setIdVendaProduto(Integer idVendaProduto) {
		this.idVendaProduto = idVendaProduto;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Double getPrecoVendaProduto() {
		return precoVendaProduto;
	}

	public void setPrecoVendaProduto(Double precoVendaProduto) {
		this.precoVendaProduto = precoVendaProduto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
}
