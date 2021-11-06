package br.ufg.inf.fs.dto.response;

import br.ufg.inf.fs.enums.Grupo;
import br.ufg.inf.fs.enums.UnidadeMedida;

public class ProdutoEstoqueResponse {
    
    private Integer idProdutoEstoque;
    private Grupo grupo;
    private String nmProduto;
    private UnidadeMedida unidadeMedida;
    private Double prUnitario;
    private Integer qtdEstoque;
    private Integer qtdReservada;


    public ProdutoEstoqueResponse() {
    }

    public ProdutoEstoqueResponse(
            Integer idProdutoEstoque, Grupo grupo, String nmProduto, UnidadeMedida unidadeMedida, Double prUnitario, Integer qtdEstoque, Integer qtdReservada) {
        this.idProdutoEstoque = idProdutoEstoque;
        this.grupo = grupo;
        this.nmProduto = nmProduto;
        this.unidadeMedida = unidadeMedida;
        this.prUnitario = prUnitario;
        this.qtdEstoque = qtdEstoque;
        this.qtdReservada = qtdReservada;
    }

    public Integer getIdProdutoEstoque() {
        return this.idProdutoEstoque;
    }

    public void setIdProdutoEstoque(Integer idProdutoEstoque) {
        this.idProdutoEstoque = idProdutoEstoque;
    }

    public Grupo getGrupo() {
        return this.grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getNmProduto() {
        return this.nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public UnidadeMedida getUnidadeMedida() {
        return this.unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Double getPrUnitario() {
        return this.prUnitario;
    }

    public void setPrUnitario(Double prUnitario) {
        this.prUnitario = prUnitario;
    }

    public Integer getQtdEstoque() {
        return this.qtdEstoque;
    }

    public void setQtdEstoque(Integer qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public Integer getQtdReservada() {
        return this.qtdReservada;
    }

    public void setQtdReservada(Integer qtdReservada) {
        this.qtdReservada = qtdReservada;
    }

    public ProdutoEstoqueResponse idProdutoEstoque(Integer idProdutoEstoque) {
        setIdProdutoEstoque(idProdutoEstoque);
        return this;
    }

    public ProdutoEstoqueResponse grupo(Grupo grupo) {
        setGrupo(grupo);
        return this;
    }

    public ProdutoEstoqueResponse nmProduto(String nmProduto) {
        setNmProduto(nmProduto);
        return this;
    }

    public ProdutoEstoqueResponse unidadeMedida(UnidadeMedida unidadeMedida) {
        setUnidadeMedida(unidadeMedida);
        return this;
    }

    public ProdutoEstoqueResponse prUnitario(Double prUnitario) {
        setPrUnitario(prUnitario);
        return this;
    }

    public ProdutoEstoqueResponse qtdEstoque(Integer qtdEstoque) {
        setQtdEstoque(qtdEstoque);
        return this;
    }

    public ProdutoEstoqueResponse qtdReservada(Integer qtdReservada) {
        setQtdReservada(qtdReservada);
        return this;
    }
}
