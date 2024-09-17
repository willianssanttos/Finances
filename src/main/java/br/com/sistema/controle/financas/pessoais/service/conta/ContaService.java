package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.ContaDao;
import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.ContaDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.SaldoDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.TipoContaImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.dao.conta.TipoContaDao;
import br.com.sistema.controle.financas.pessoais.exception.ServiceException;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

import java.util.List;

public class ContaService {

    private ContaDao contaDao;
    private SaldoDao saldoDao;
    private TipoContaDao tipoContaDao;
    public ContaService(){
        this.contaDao = new ContaDaoImpl();
        this.saldoDao = new SaldoDaoImpl();
        this.tipoContaDao = new TipoContaImpl();
    }

    public ContaEntity criarConta(ContaEntity conta){
            return contaDao.criarConta(conta);
    }

    public List<String> obterTodosTiposConta() {
            return tipoContaDao.obterTiposConta();
    }

    public void editarConta(ContaEntity conta){
            contaDao.editarConta(conta);
    }

    public void excluirConta(Integer idConta){
            contaDao.excluirConta(idConta);
    }

    public Double obterSaldo(Integer idUsuario){
            return saldoDao.obterSaldoPorIdUsuario(idUsuario);
    }

    public List<ContaEntity> obterContasPorIdUsuario(Integer idUsuario){
            return contaDao.obterContasPorUsuario(idUsuario);
    }
}
