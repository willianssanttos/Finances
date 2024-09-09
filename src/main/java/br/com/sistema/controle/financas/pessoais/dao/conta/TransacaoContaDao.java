package br.com.sistema.controle.financas.pessoais.dao.conta;

import br.com.sistema.controle.financas.pessoais.model.conta.TransacoesContaEntity;

public interface TransacaoContaDao {
    void inserirTransacao(TransacoesContaEntity transacao);
}
