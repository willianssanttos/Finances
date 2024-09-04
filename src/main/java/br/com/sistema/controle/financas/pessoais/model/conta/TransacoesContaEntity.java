package br.com.sistema.controle.financas.pessoais.model.conta;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransacoesContaEntity {

    private Integer idTransacao;
    private Integer idSaldo;
    private Integer idConta;
    private String Descricao;
    private Double Valor;
    private Timestamp dataMovimentacao;
    private int tipo;

    public Integer getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Integer idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Integer getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(Integer idSaldo) {
        this.idSaldo = idSaldo;
    }

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double valor) {
        Valor = valor;
    }

    public Timestamp getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Timestamp dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
