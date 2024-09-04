package br.com.sistema.controle.financas.pessoais.model.conta;

import java.sql.Timestamp;

public class ContaEntity {

    private Integer idConta;
    private Integer idUsuario;
    private Integer idSaldo;
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

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(Integer idSaldo) {
        this.idSaldo = idSaldo;
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

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Timestamp getDataDeposito() {
        return dataDeposito;
    }

    public void setDataDeposito(Timestamp dataDeposito) {
        this.dataDeposito = dataDeposito;
    }

}


