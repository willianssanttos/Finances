//package br.com.sistema.controle.financas.pessoais.adapter.output.conta;
//
//import br.com.sistema.controle.financas.pessoais.port.output.conta.ITransacaoContaDao;
//import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ExtratoEntity;
//import br.com.sistema.controle.financas.pessoais.domain.entity.conta.TransacoesContaEntity;
//import br.com.sistema.controle.financas.pessoais.utils.Constantes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TransacaoContaDaoImpl implements ITransacaoContaDao {
//    private static final Logger logger = LoggerFactory.getLogger(TransacaoContaDaoImpl.class);
//    public void inserirTransacao(TransacoesContaEntity transacao){
//        logger.debug(Constantes.DebugRegistroProcesso + transacao);
//
//        String sql = "SELECT inserir_transacao(?,?,?,?,?,?,?)";
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, transacao.getIdSaldo());
//            ps.setInt(2, transacao.getIdConta());
//            ps.setString(3, transacao.getDescricao());
//            ps.setString(4, transacao.getCategoria());
//            ps.setDouble(5, transacao.getValor());
//            ps.setTimestamp(6, transacao.getDataMovimentacao());
//            ps.setInt(7, transacao.getTipo());
//
//            ps.executeQuery();
//
//            logger.info(Constantes.InfoRegistrar + transacao);
//        } catch (SQLException e){
//            logger.error(Constantes.ErroRegistrarNoServidor);
//        }
//    }
//
//    public List<ExtratoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano){
//        logger.debug(Constantes.DebugBuscarProcesso + idUsuario);
//
//        String sql = "SELECT * FROM buscar_extrato_por_usuario(?,?,?)";
//
//        List<ExtratoEntity> extratos = new ArrayList<>();
//
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, idUsuario);
//            ps.setInt(2, mes);
//            ps.setInt(3, ano);
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                ExtratoEntity extrato = new ExtratoEntity();
//                extrato.setNomeConta(rs.getString("nm_nome"));
//                extrato.setDescricao(rs.getString("ds_descricao"));
//                extrato.setCategoria(rs.getString("ds_categoria"));
//                extrato.setValor(rs.getDouble("ds_valor"));
//                extrato.setDataMovimentacao(rs.getTimestamp("ds_data_movimentacao"));
//                extrato.setTipoTransacao(rs.getInt("tipo") == 1 ? "Ganho" : "Gasto");
//                extrato.setTipoConta(rs.getString("nm_tipo_conta"));
//                extratos.add(extrato);
//            }
//
//            logger.info(Constantes.InfoBuscar + idUsuario);
//        } catch (SQLException e){
//            logger.error(Constantes.ErroBuscarRegistroNoServidor);
//        }
//        return extratos;
//    }
//}
