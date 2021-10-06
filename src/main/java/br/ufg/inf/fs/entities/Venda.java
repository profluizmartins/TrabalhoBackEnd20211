package br.ufg.inf.fs.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.ufg.inf.fs.enums.TipoPagamento;

@Entity
@Table(name="tb_venda")
public class Venda  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_venda")
	private Integer idVenda;
	
	@OneToOne
	@JoinColumn(name="comprador_id")
	private Pessoa comprador;

	@Column(name="data_venda")
	private Date dataVenda;
	
	private Boolean concluido;
	
	@Column(name="tipo_pagamento")
	private TipoPagamento tipoPagamento;
	
	@Column(name="qtd_prestacao")
	private Integer qtdPrestacao;

	public Venda() {
		super();
	}

	public Venda(Integer idVenda, Pessoa comprador, Date dataVenda, Boolean concluido, TipoPagamento tipoPagamento,
			Integer qtdPrestacao) {
		super();
		this.idVenda = idVenda;
		this.comprador = comprador;
		this.dataVenda = dataVenda;
		this.concluido = concluido;
		this.tipoPagamento = tipoPagamento;
		this.qtdPrestacao = qtdPrestacao;
	}

	public Integer getIdVenda() {
		return idVenda;
	}

	public void setIdVenda(Integer idVenda) {
		this.idVenda = idVenda;
	}

	public Pessoa getComprador() {
		return comprador;
	}

	public void setComprador(Pessoa comprador) {
		this.comprador = comprador;
	}

	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Integer getQtdPrestacao() {
		return qtdPrestacao;
	}

	public void setQtdPrestacao(Integer qtdPrestacao) {
		this.qtdPrestacao = qtdPrestacao;
	}
	
}
