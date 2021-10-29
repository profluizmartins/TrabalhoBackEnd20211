package br.ufg.inf.fs.entities;

import br.ufg.inf.fs.enums.TipoPagamento;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.persistence.*;

@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String login;
	
	private String senha;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="usuario_regra",
		joinColumns =  @JoinColumn(name="login", referencedColumnName = "login"),
		inverseJoinColumns = @JoinColumn(name="regra", referencedColumnName = "regra")
	)
	private List<Regra> regras;

	@OneToOne
	@JoinColumn(name="user_id")
	private Pessoa pessoa;

	
	public Usuario(String login, String senha, Pessoa pessoa, List<Regra> regras) {
		super();
		this.login = login;
		this.senha = senha;
		this.pessoa = pessoa;
		System.out.println("*********************************");
		System.out.println("Tamnho da lista:" + regras.size());
		if(regras.size() == 0 || regras == null || notContainsComum(regras)){
			this.regras = regras;
			this.regras.add(new Regra("Comum"));
		} else{
			this.regras = regras;
		}

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

	public Pessoa getPessoa() { return pessoa; }

	public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }
	
	private boolean notContainsComum(List<Regra> regras){
		for(int i = 0; i < regras.size(); i++){
			if(regras.get(i).getRegra().equals("COMUM")) return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"login='" + login + '\'' +
				", senha='" + senha + '\'' +
				", regras=" + regras +
				", pessoa=" + pessoa +
				'}';
	}
}
