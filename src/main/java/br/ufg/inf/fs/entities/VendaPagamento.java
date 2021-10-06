package br.ufg.inf.fs.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_venda_pagamento")
public class VendaPagamento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_venda_pagamento")
	private Integer idVendaPagamento;
	
	@Column(name="valor_pagamento")
	private Double vlPagamento;

	@Column(name="data_pagamento")
	private Date dtPagamento;
	
	@ManyToOne
	@JoinColumn(name="venda_id")
	private VendaFatura vendaFatura;
	

	public VendaPagamento() {
		super();
	}

	public VendaPagamento(Integer idVendaPagamento, Double vlPagamento, Date dtPagamento, VendaFatura vendaFatura) {
		super();
		this.idVendaPagamento = idVendaPagamento;
		this.vlPagamento = vlPagamento;
		this.dtPagamento = dtPagamento;
		this.vendaFatura = vendaFatura;
	}

	public Integer getIdVendaPagamento() {
		return idVendaPagamento;
	}

	public void setIdVendaPagamento(Integer idVendaPagamento) {
		this.idVendaPagamento = idVendaPagamento;
	}

	public Double getVlPagamento() {
		return vlPagamento;
	}

	public void setVlPagamento(Double vlPagamento) {
		this.vlPagamento = vlPagamento;
	}

	public Date getDtPagamento() {
		return dtPagamento;
	}

	public void setDtPagamento(Date dtPagamento) {
		this.dtPagamento = dtPagamento;
	}

	public VendaFatura getVendaFatura() {
		return vendaFatura;
	}

	public void setVendaFatura(VendaFatura vendaFatura) {
		this.vendaFatura = vendaFatura;
	}
		
}
