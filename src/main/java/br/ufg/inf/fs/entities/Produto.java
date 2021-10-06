package br.ufg.inf.fs.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import br.ufg.inf.fs.enums.Grupo;
import br.ufg.inf.fs.enums.UnidadeMedida;

@Entity
@Table(name="tb_produto")
public class Produto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_produto")
	private Integer idProduto;
	
	@Column(name="unidade_medida")
	private UnidadeMedida unidadeMedida;
	
	private Grupo grupo;
	
	@Column(name="nome_produto")
	private String nmProduto;
	
	@Column(name="desc_produto")
	private String dsProduto;

	@Column(name="preco_unitario")
	private Double prUnitario;

	public Produto() {
		super();
	}

	public Produto(Integer idProduto, UnidadeMedida unidadeMedida, Grupo grupo, String nmProduto, String dsProduto,
			Double prUnitario) {
		super();
		this.idProduto = idProduto;
		this.unidadeMedida = unidadeMedida;
		this.grupo = grupo;
		this.nmProduto = nmProduto;
		this.dsProduto = dsProduto;
		this.prUnitario = prUnitario;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public String getNmProduto() {
		return nmProduto;
	}

	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public Double getPrUnitario() {
		return prUnitario;
	}

	public void setPrUnitario(Double prUnitario) {
		this.prUnitario = prUnitario;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", unidadeMedida=" + unidadeMedida + ", grupo=" + grupo
				+ ", nmProduto=" + nmProduto + ", dsProduto=" + dsProduto + ", prUnitario=" + prUnitario + "]";
	}

}
