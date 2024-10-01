//package br.com.sistema.controle.financas.pessoais.domain.command.conta;
//
//import br.com.sistema.controle.financas.pessoais.port.output.conta.IContaDao;
//import br.com.sistema.controle.financas.pessoais.adapter.output.conta.ContaDaoImpl;
//import br.com.sistema.controle.financas.pessoais.adapter.output.conta.SaldoDaoImpl;
//import br.com.sistema.controle.financas.pessoais.adapter.output.conta.TipoContaImpl;
//import br.com.sistema.controle.financas.pessoais.port.output.conta.ISaldoDao;
//import br.com.sistema.controle.financas.pessoais.port.output.conta.ITipoContaDao;
//import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;
//import br.com.sistema.controle.financas.pessoais.utils.Constantes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.List;
//
//public class ContaService {
//
//    private static final Logger logger = LoggerFactory.getLogger(ContaService.class);
//    private IContaDao IContaDao;
//    private ISaldoDao ISaldoDao;
//    private ITipoContaDao ITipoContaDao;
//    public ContaService(){
//        this.IContaDao = new ContaDaoImpl();
//        this.ISaldoDao = new SaldoDaoImpl();
//        this.ITipoContaDao = new TipoContaImpl();
//    }
//
//    public ContaEntity criarConta(ContaEntity conta){
//        try {
//            return IContaDao.criarConta(conta);
//        } catch (Exception e){
//            logger.error(Constantes.ErroCadastroConta);
//        }
//        return conta;
//    }
//
//    public List<String> obterTodosTiposConta() {
//        try {
//            return ITipoContaDao.obterTiposConta();
//        } catch (Exception e){
//            logger.error(Constantes.cadastroTipoConta);
//        }
//        return obterTodosTiposConta();
//    }
//
//    public void editarConta(ContaEntity conta){
//        try {
//            IContaDao.editarConta(conta);
//        } catch (Exception e){
//            logger.error(Constantes.ErroEditar);
//        }
//    }
//
//    public void excluirConta(Integer idConta){
//        try {
//            IContaDao.excluirConta(idConta);
//        } catch (Exception e){
//            logger.error(Constantes.ErroExcluir);
//        }
//    }
//
//    public Double obterSaldo(Integer idUsuario){
//        try {
//            return ISaldoDao.obterSaldoPorIdUsuario(idUsuario);
//        } catch (Exception e){
//            logger.error(Constantes.ErrorRecuperarSaldo);
//        }
//        return obterSaldo(idUsuario);
//    }
//
//    public List<ContaEntity> obterContasPorIdUsuario(Integer idUsuario){
//        try {
//            return IContaDao.obterContasPorUsuario(idUsuario);
//        } catch (Exception e){
//           logger.error(Constantes.ErrorRecuperarContas);
//        }
//        return obterContasPorIdUsuario(idUsuario);
//    }
//}
