package br.ufg.inf.fs.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class PessoaFisica implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pessoa_fisica")
	private Integer idPessoaFisica;
	
	private Long cpf;
	
	@Column(name="data_nascimento")
	private Calendar dtNascimento;
	
	private String genero;
	
	@ManyToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;

	public PessoaFisica() {
		super();
	}

	public PessoaFisica(Integer idPessoaFisica, Long cpf, Calendar dtNascimento, String genero, Pessoa pessoa) {
		super();
		this.idPessoaFisica = idPessoaFisica;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.genero = genero;
		this.pessoa = pessoa;
	}

	public Integer getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Integer idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Calendar getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Calendar dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
}
