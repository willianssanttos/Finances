package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.ContaDao;
import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.ContaDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.SaldoDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.model.conta.SaldoEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ContaService {

    private ContaDao contaDao;
    private SaldoDao saldoDao;
    public ContaService(){
        this.contaDao = new ContaDaoImpl();
        this.saldoDao = new SaldoDaoImpl();
    }

   public ContaEntity criarConta(ContaEntity conta){

        conta.getIdUsuario();
        conta.getIdSaldo();
        conta.getNomeConta();
        conta.getSaldoConta();
        conta.getTipoConta();
        conta.getDataDeposito();

        ContaEntity novaConta = contaDao.criarConta(conta);

        return novaConta;
    }

    public Double obterSaldo(Integer idConta){
        return saldoDao.obterSaldoPorIdUsuario(idConta);
    }
}
