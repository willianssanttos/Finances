package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.ContaDao;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.model.conta.SaldoEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ContaService {

    private ContaDao contaDao;
    private SaldoDao saldoDao;
    public ContaService(){
        this.contaDao = new ContaDao();
        this.saldoDao = new SaldoDao();
    }

   public ContaEntity criarConta(ContaEntity conta){

        conta.getIdUsuario();
        conta.getIdSaldo();
        conta.getNomeConta();
        conta.getSaldoConta();
        conta.getTipoConta();
        conta.getDataDeposito();

        ContaEntity novaConta = contaDao.criarConta(conta);

        if (novaConta.getIdConta() != null){
            SaldoEntity inserirSaldo = new SaldoEntity();
            inserirSaldo.setIdUsuario(novaConta.getIdUsuario());
            inserirSaldo.setSaldoAtual(novaConta.getSaldoConta());
            inserirSaldo.setDataAtualizadaSaldo(novaConta.getDataDeposito());

            saldoDao.inserirSaldo(inserirSaldo);
        }
        return novaConta;
    }

    public Double obterSaldo(Integer idConta){
        return saldoDao.obterSaldoPorIdUsuario(idConta);
    }
}
