package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.ContaDao;
import br.com.sistema.controle.financas.pessoais.dao.conta.impl.ContaDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.impl.SaldoDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.impl.TipoContaImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.dao.conta.TipoContaDao;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ContaService {

    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);
    private ContaDao contaDao;
    private SaldoDao saldoDao;
    private TipoContaDao tipoContaDao;
    public ContaService(){
        this.contaDao = new ContaDaoImpl();
        this.saldoDao = new SaldoDaoImpl();
        this.tipoContaDao = new TipoContaImpl();
    }

    public ContaEntity criarConta(ContaEntity conta){
        try {
            return contaDao.criarConta(conta);
        } catch (Exception e){
            logger.error(Constantes.ErroCadastroConta);
        }
        return conta;
    }

    public List<String> obterTodosTiposConta() {
        try {
            return tipoContaDao.obterTiposConta();
        } catch (Exception e){
            logger.error(Constantes.cadastroTipoConta);
        }
        return obterTodosTiposConta();
    }

    public void editarConta(ContaEntity conta){
        try {
            contaDao.editarConta(conta);
        } catch (Exception e){
            logger.error(Constantes.ErroEditar);
        }
    }

    public void excluirConta(Integer idConta){
        try {
            contaDao.excluirConta(idConta);
        } catch (Exception e){
            logger.error(Constantes.ErroExcluir);
        }
    }

    public Double obterSaldo(Integer idUsuario){
        try {
            return saldoDao.obterSaldoPorIdUsuario(idUsuario);
        } catch (Exception e){
            logger.error(Constantes.ErrorRecuperarSaldo);
        }
        return obterSaldo(idUsuario);
    }

    public List<ContaEntity> obterContasPorIdUsuario(Integer idUsuario){
        try {
            return contaDao.obterContasPorUsuario(idUsuario);
        } catch (Exception e){
           logger.error(Constantes.ErrorRecuperarContas);
        }
        return obterContasPorIdUsuario(idUsuario);
    }
}
