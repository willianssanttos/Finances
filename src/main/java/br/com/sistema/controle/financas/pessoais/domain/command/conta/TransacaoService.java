//package br.com.sistema.controle.financas.pessoais.domain.command.conta;
//
//import br.com.sistema.controle.financas.pessoais.adapter.output.conta.TransacaoContaDaoImpl;
//import br.com.sistema.controle.financas.pessoais.port.output.conta.ITransacaoContaDao;
//import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ExtratoEntity;
//import br.com.sistema.controle.financas.pessoais.domain.entity.conta.TransacoesContaEntity;
//import br.com.sistema.controle.financas.pessoais.utils.Constantes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class TransacaoService {
//    private ITransacaoContaDao ITransacaoContaDao;
//    public TransacaoService(){
//        this.ITransacaoContaDao = new TransacaoContaDaoImpl();
//    }
//    private static final Logger logger = LoggerFactory.getLogger(TransacaoService.class);
//    public void registrarTransacao(Integer idConta, Integer idSaldo, String descricao, String categoria, Double valor, int tipo){
//           try {
//               if (tipo != 1 && tipo != 2){
//                   logger.error(Constantes.tipoTransacao);
//               }
//
//               TransacoesContaEntity novaTransacao = new TransacoesContaEntity();
//               novaTransacao.setIdConta(idConta);
//               novaTransacao.setIdSaldo(idSaldo);
//               novaTransacao.setDescricao(descricao);
//               novaTransacao.setCategoria(categoria);
//               novaTransacao.setValor(valor);
//               novaTransacao.setTipo(tipo);
//               novaTransacao.setDataMovimentacao(Timestamp.valueOf(LocalDateTime.now()));
//
//               ITransacaoContaDao.inserirTransacao(novaTransacao);
//           } catch (Exception e){
//               logger.error(Constantes.ErrocadastroTransacao);
//           }
//    }
//
//    public List<ExtratoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano){
//        try {
//            return ITransacaoContaDao.obterExtratoPorMes(idUsuario, mes, ano);
//        } catch (Exception e){
//            logger.error(Constantes.ErrorRecuperarExtrato);
//        }
//        return obterExtratoPorMes(idUsuario, mes, ano);
//    }
//}
