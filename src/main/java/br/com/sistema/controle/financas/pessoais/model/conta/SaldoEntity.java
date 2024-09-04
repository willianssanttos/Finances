package br.com.sistema.controle.financas.pessoais.model.conta;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class SaldoEntity {

    private Integer idSaldo;
    private Integer idUsuario;
    private Double saldoAtual;
    private Timestamp dataAtualizadaSaldo;

    public Integer getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(Integer idSaldo) {
        this.idSaldo = idSaldo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getSaldoAtual() {
        return saldoAtual;
    }

    public void setSaldoAtual(Double saldoAtual) {
        this.saldoAtual = saldoAtual;
    }

    public Timestamp getDataAtualizadaSaldo() {
        return dataAtualizadaSaldo;
    }

    public void setDataAtualizadaSaldo(Timestamp dataAtualizadaSaldo) {
        this.dataAtualizadaSaldo = dataAtualizadaSaldo;
    }
}
