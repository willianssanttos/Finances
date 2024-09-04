package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;


public class SaldoService {

    private SaldoDao saldoDao;

    public SaldoService(){
        this.saldoDao = new SaldoDao();
    }


}
