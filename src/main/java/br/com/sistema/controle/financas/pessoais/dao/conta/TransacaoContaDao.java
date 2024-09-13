package br.com.sistema.controle.financas.pessoais.dao.conta;

import br.com.sistema.controle.financas.pessoais.model.conta.ExtratoEntity;
import br.com.sistema.controle.financas.pessoais.model.conta.TransacoesContaEntity;

import java.util.List;

public interface TransacaoContaDao {
    void inserirTransacao(TransacoesContaEntity transacao);
    List<ExtratoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano);
}
