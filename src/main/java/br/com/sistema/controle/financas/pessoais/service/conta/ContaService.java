package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.ContaDao;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;

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
           saldoDao.inserirSaldo(
                    novaConta.getIdUsuario(),
                    novaConta.getSaldoConta(),
                    novaConta.getDataDeposito());
        }
        return novaConta;
    }

    public List<ContaEntity>obterContasPorUsuario(Integer idUsuario){
        return contaDao.obterContasPorUsuario(idUsuario);
    }

    public Integer obterSaldo(Integer idConta){
        return saldoDao.obterSaldoPorIdConta(idConta);
    }
}
