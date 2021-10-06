package br.ufg.inf.fs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="tb_pessoa_juridica")
public class PessoaJuridica implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pessoa_juridica")
	private Integer idPessoaJuridica;
	
	private Long cnpj;
	
	private String atividade;

	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;
	
	public PessoaJuridica() {
		super();
	}

	public PessoaJuridica(Integer idPessoaJuridica, Long cnpj, String atividade, Pessoa pessoa) {
		super();
		this.idPessoaJuridica = idPessoaJuridica;
		this.cnpj = cnpj;
		this.atividade = atividade;
		this.pessoa = pessoa;
	}

	public Integer getIdPessoaJuridica() {
		return idPessoaJuridica;
	}

	public void setIdPessoaJuridica(Integer idPessoaJuridica) {
		this.idPessoaJuridica = idPessoaJuridica;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
