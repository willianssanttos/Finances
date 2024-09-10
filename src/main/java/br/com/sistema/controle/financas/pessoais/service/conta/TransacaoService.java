package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.TransacaoContaDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.TransacaoContaDao;
import br.com.sistema.controle.financas.pessoais.handler.ServiceException;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.model.conta.TransacoesContaEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class TransacaoService {
    private TransacaoContaDao transacaoContaDao;
    public TransacaoService(){
        this.transacaoContaDao = new TransacaoContaDaoImpl();
    }

    public void registrarTransacao(Integer idConta, Integer idSaldo, String descricao, Double valor, int tipo){
        try {
            if (tipo != 1 && tipo != 2){
                throw new IllegalArgumentException(Constantes.tipoTransacao);
            }

            TransacoesContaEntity novaTransacao = new TransacoesContaEntity();
            novaTransacao.setIdConta(idConta);
            novaTransacao.setIdSaldo(idSaldo);
            novaTransacao.setDescricao(descricao);
            novaTransacao.setValor(valor);
            novaTransacao.setTipo(tipo);
            novaTransacao.setDataMovimentacao(Timestamp.valueOf(LocalDateTime.now()));

            transacaoContaDao.inserirTransacao(novaTransacao);
        } catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(Constantes.ErrocadastroTransacao, e);
        }
    }
}
