package br.ufg.inf.fs.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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
@Table(name="tb_entrada")
public class Entrada implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_entrada")
	private Integer idEntrada;
	
	private LocalDate dataEntrada;
	
	@ManyToOne
	@JoinColumn(name="pessoa_juridica_id")
	private PessoaJuridica fornecedor;
	
	private Boolean concluido;

	public Entrada() {
		super();
	}

	public Entrada(Integer idEntrada, LocalDate dataEntrada, PessoaJuridica fornecedor, Boolean concluido) {
		super();
		this.idEntrada = idEntrada;
		this.dataEntrada = dataEntrada;
		this.fornecedor = fornecedor;
		this.concluido = concluido;
	}

	public Integer getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(Integer idEntrada) {
		this.idEntrada = idEntrada;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public PessoaJuridica getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(PessoaJuridica fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Boolean getConcluido() {
		return concluido;
	}

	public void setConcluido(Boolean concluido) {
		this.concluido = concluido;
	}
		
}
