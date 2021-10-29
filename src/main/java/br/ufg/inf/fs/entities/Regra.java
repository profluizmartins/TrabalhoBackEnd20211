package br.ufg.inf.fs.entities;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_regra")
public class Regra implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String regra;

	public Regra() {
		super();
	}

	public Regra(String regra) {
		super();
		this.regra = regra.toUpperCase(Locale.ROOT);
	}

	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra.toUpperCase(Locale.ROOT);
	}

	@Override
	public String toString() {
		return "Regra{" +
				"regra='" + regra + '\'' +
				'}';
	}
}
