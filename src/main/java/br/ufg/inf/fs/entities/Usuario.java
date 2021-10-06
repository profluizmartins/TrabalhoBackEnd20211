package br.ufg.inf.fs.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String login;
	
	private String senha;
	
	@OneToOne
	@JoinColumn(name="pessoa_id")
	private Pessoa pessoa;

	@ManyToMany
	@JoinTable(name="usuario_regra",
		joinColumns =  @JoinColumn(name="login", referencedColumnName = "login"),
		inverseJoinColumns = @JoinColumn(name="regra", referencedColumnName = "regra")
	)
	private List<Regra> regras;
	
	
	public Usuario(String login, String senha, Pessoa pessoa, List<Regra> regras) {
		super();
		this.login = login;
		this.senha = senha;
		this.pessoa = pessoa;
		this.regras = regras;
	}

	public Usuario() {
		super();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Regra> getRegras() {
		return regras;
	}

	public void setRegras(List<Regra> regras) {
		this.regras = regras;
	}	

}
