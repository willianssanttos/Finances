package br.com.sistema.controle.financas.pessoais.port.output.conta;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;

public interface ISaldoDao {

    SaldoEntity inserirSaldo(SaldoEntity saldo);
//    Double obterSaldoPorIdUsuario(Integer idUsuario);
}
