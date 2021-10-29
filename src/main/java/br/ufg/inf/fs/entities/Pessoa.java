package br.ufg.inf.fs.entities;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="tb_pessoa")
public class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_user")
	private Integer idPessoa;
	
	private String nome;
	
	private String endereco;

	public Pessoa() {
		super();
	}

	public Pessoa(Integer idPessoa, String nome, String endereco) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.endereco = endereco;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Pessoa{" +
				"idPessoa=" + idPessoa +
				", nome='" + nome + '\'' +
				", endereco='" + endereco + '\'' +
				'}';
	}
}
