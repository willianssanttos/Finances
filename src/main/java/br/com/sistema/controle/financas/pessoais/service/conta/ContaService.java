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

            conta.getIdUsuario();
            conta.getIdSaldo();
            conta.getNomeConta();
            conta.getSaldoConta();
            conta.setTipoConta(tipoConta);
            conta.getDataDeposito();

            ContaEntity novaConta = contaDao.criarConta(conta);

            return novaConta;
        } catch (Exception e){
            throw new ServiceException(Constantes.ErroCadastroConta, e);
        }
    }

    public Double obterSaldo(Integer idConta){
        try {
            return saldoDao.obterSaldoPorIdUsuario(idConta);
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