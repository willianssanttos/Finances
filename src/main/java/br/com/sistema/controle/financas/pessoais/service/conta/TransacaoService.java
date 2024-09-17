package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.TransacaoContaDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.TransacaoContaDao;
import br.com.sistema.controle.financas.pessoais.exception.ServiceException;
import br.com.sistema.controle.financas.pessoais.model.conta.ExtratoEntity;
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

    public void registrarTransacao(Integer idConta, Integer idSaldo, String descricao, String categoria, Double valor, int tipo){
            if (tipo != 1 && tipo != 2){
                throw new IllegalArgumentException(Constantes.tipoTransacao);
            }

            TransacoesContaEntity novaTransacao = new TransacoesContaEntity();
            novaTransacao.setIdConta(idConta);
            novaTransacao.setIdSaldo(idSaldo);
            novaTransacao.setDescricao(descricao);
            novaTransacao.setCategoria(categoria);
            novaTransacao.setValor(valor);
            novaTransacao.setTipo(tipo);
            novaTransacao.setDataMovimentacao(Timestamp.valueOf(LocalDateTime.now()));

            transacaoContaDao.inserirTransacao(novaTransacao);
    }

    public List<ExtratoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano){
            return transacaoContaDao.obterExtratoPorMes(idUsuario, mes, ano);
    }
}
