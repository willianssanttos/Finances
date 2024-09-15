package br.com.sistema.controle.financas.pessoais.service.conta;

import br.com.sistema.controle.financas.pessoais.dao.conta.ContaDao;
import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.ContaDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.SaldoDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.TipoContaImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.dao.conta.TipoContaDao;
import br.com.sistema.controle.financas.pessoais.handler.ServiceException;
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
        try {
            String tipoConta = tipoContaDao.obterTipoConta(conta.getTipoConta());
            if (tipoConta == null){
                throw new IllegalArgumentException(Constantes.cadastroTipoConta);
            }
            conta.setTipoConta(tipoConta);

            return contaDao.criarConta(conta);
        } catch (Exception e){
            throw new ServiceException(Constantes.ErroCadastroConta, e);
        }
    }

    public void editarConta(ContaEntity conta){
        try {
            contaDao.editarConta(conta);
        } catch (Exception e){
            throw  new ServiceException(Constantes.ErroEditar, e);
        }
    }

    public void excluirConta(Integer idConta){
        try {
            contaDao.excluirConta(idConta);
        } catch (Exception e){
            throw new ServiceException(Constantes.ErroExcluir, e);
        }
    }

    public Double obterSaldo(Integer idUsuario){
        try {
            return saldoDao.obterSaldoPorIdUsuario(idUsuario);
        } catch (Exception e){
            throw new ServiceException(Constantes.ErrorRecuperarIdUsuario, e);
        }
    }

    public List<ContaEntity> obterContasPorIdUsuario(Integer idUsuario){
        try {
            return contaDao.obterContasPorUsuario(idUsuario);
        } catch (Exception e){
            throw new ServiceException(Constantes.ErrorRecuperarContas, e);
        }
    }
}
