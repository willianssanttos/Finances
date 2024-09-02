package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.ContaDao;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;

public class ContaService {

    private ContaDao contaDao;

    public ContaService(ContaDao contaDao){
        this.contaDao = contaDao;
    }

   public ContaEntity criarConta(ContaEntity conta){

        conta.getIdUsuario();
        conta.getNomeConta();
        conta.getSaldoConta();
        conta.getTipoConta();
        conta.getDataDeposito();

        return contaDao.criarConta(conta);
    }
}
