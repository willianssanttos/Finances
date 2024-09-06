package br.com.sistema.controle.financas.pessoais.dao.conta;

import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;

public interface ContaDao {

    ContaEntity criarConta(ContaEntity conta);
}
