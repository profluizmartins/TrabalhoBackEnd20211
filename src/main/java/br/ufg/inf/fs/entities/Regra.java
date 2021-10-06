package br.ufg.inf.fs.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="tb_regra")
public class Regra implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String regra;
	
	@ManyToMany(mappedBy = "regras")
	private List<Usuario> usuarios;


	public Regra() {
		super();
	}

	public Regra(String regra) {
		super();
		this.regra = regra;
	}

	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
