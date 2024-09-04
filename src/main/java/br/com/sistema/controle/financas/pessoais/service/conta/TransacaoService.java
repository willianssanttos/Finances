package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.TransacaoContaDao;
import br.com.sistema.controle.financas.pessoais.model.conta.TransacoesContaEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransacaoService {
    private TransacaoContaDao transacaoContaDao;
    public TransacaoService(){
        this.transacaoContaDao = new TransacaoContaDao();
    }

    public void registrarTransacao(Integer idSaldo, String descricao, Double valor, int tipo){
        if (tipo != 1 && tipo != 2){
            throw new IllegalArgumentException(Constantes.tipoTransacao);
        }

        TransacoesContaEntity novaTransacao = new TransacoesContaEntity();
        novaTransacao.setIdSaldo(idSaldo);
        novaTransacao.setDescricao(descricao);
        novaTransacao.setValor(valor);
        novaTransacao.setTipo(tipo);
        novaTransacao.setDataMovimentacao(Timestamp.valueOf(LocalDateTime.now()));

        transacaoContaDao.inserirTransacao(novaTransacao);
    }
}
