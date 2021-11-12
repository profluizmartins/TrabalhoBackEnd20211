package br.ufg.inf.fs.dto;

import java.util.Date;

public class CadastroVendaPagamentoDto {
    private Date date;
    private Double valor;

    public CadastroVendaPagamentoDto(Date date, Double valor) {
        this.date = date;
        this.valor = valor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
