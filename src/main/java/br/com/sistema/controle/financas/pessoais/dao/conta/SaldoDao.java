package br.com.sistema.controle.financas.pessoais.dao.conta;

import br.com.sistema.controle.financas.pessoais.model.conta.SaldoEntity;

public interface SaldoDao {

    SaldoEntity inserirSaldo(SaldoEntity saldo);
    Double obterSaldoPorIdUsuario(Integer idUsuario);
}
