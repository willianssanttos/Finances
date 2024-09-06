package br.com.sistema.controle.financas.pessoais.model.conta;

import br.com.sistema.controle.financas.pessoais.enuns.TipoContaEnum;

import java.sql.Timestamp;

public class ContaEntity extends SaldoEntity{

    private Integer idConta;
    private String nomeConta;
    private Double saldoConta;
    private String tipoConta;
    private Timestamp dataDeposito;

    public Integer getIdConta() {
        return idConta;
    }

    public void setIdConta(Integer idConta) {
        this.idConta = idConta;
    }
    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public Double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(Double saldoConta) {
        this.saldoConta = saldoConta;
    }

    public Timestamp getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(Timestamp dataDeposito) {
        this.dataDeposito = dataDeposito;
    }

}


