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
@Table(name="tb_venda_fatura")
public class VendaFatura  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_venda_fatura")
	private Integer idVendaFatura;
	
	
	@ManyToOne
	@JoinColumn(name="venda_id")
	private Venda venda;
		
	@Column(name="valor_venda")
	private Double valorVenda;
	
	private Boolean quitado;
	
	private Double desconto;

	public VendaFatura() {
		super();
	}

	public VendaFatura(Integer idVendaFatura, Venda venda, Double valorVenda, Boolean quitado, Double desconto) {
		super();
		this.idVendaFatura = idVendaFatura;
		this.venda = venda;
		this.valorVenda = valorVenda;
		this.quitado = quitado;
		this.desconto = desconto;
	}

	public Integer getIdVendaFatura() {
		return idVendaFatura;
	}

	public void setIdVendaFatura(Integer idVendaFatura) {
		this.idVendaFatura = idVendaFatura;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Boolean getQuitado() {
		return quitado;
	}

	public void setQuitado(Boolean quitado) {
		this.quitado = quitado;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	
}
