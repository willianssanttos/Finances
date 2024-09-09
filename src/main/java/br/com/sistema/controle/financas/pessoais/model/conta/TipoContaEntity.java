package br.com.sistema.controle.financas.pessoais.model.conta;

public class TipoContaEntity {

    private Integer idTipoConta;
    private String nomeTipoConta;

    public Integer getIdTipoConta() {
        return idTipoConta;
    }

    public void setIdTipoConta(Integer idTipoConta) {
        this.idTipoConta = idTipoConta;
    }

    public String getNomeTipoConta() {
        return nomeTipoConta;
    }

    public void setNomeTipoConta(String nomeTipoConta) {
        this.nomeTipoConta = nomeTipoConta;
    }
}
