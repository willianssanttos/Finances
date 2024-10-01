//package br.com.sistema.controle.financas.pessoais.adapter.output.conta;
//
//import br.com.sistema.controle.financas.pessoais.port.output.conta.IContaDao;
//import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;
//import br.com.sistema.controle.financas.pessoais.utils.Constantes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ContaDaoImpl implements IContaDao {
//    private static final Logger logger = LoggerFactory.getLogger(ContaDaoImpl.class);
//
//    public ContaEntity criarConta(ContaEntity conta){
//        logger.debug(Constantes.DebugRegistroProcesso + conta);
//
//        String sql = "SELECT inserir_conta(?,?,?,?,?,?)";
//
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, conta.getIdUsuario());
//            ps.setInt(2, conta.getIdSaldo());
//            ps.setString(3, conta.getTipoConta());
//            ps.setString(4, conta.getNomeConta());
//            ps.setDouble(5, conta.getSaldoConta());
//            ps.setTimestamp(6, conta.getDataDeposito());
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()){
//                int idConta = rs.getInt(1);
//                conta.setIdConta(idConta);
//            }
//            rs.close();
//
//            logger.info(Constantes.InfoRegistrar + conta);
//        } catch (SQLException e){
//            logger.error(Constantes.ErroRegistrarNoServidor);
//        }
//        return conta;
//    }
//
//    public List<ContaEntity> obterContasPorUsuario(Integer idUsuario) {
//        logger.debug(Constantes.DebugBuscarProcesso + idUsuario);
//
//        String sql = "SELECT * FROM buscar_contas_por_usuario(?)";
//        List<ContaEntity> contas = new ArrayList<>();
//
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, idUsuario);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                ContaEntity conta = new ContaEntity();
//                conta.setIdConta(rs.getInt("nr_id_conta"));
//                conta.setIdSaldo(rs.getInt("fk_nr_id_saldo"));
//                conta.setTipoConta(rs.getString("nm_tipo_conta"));
//                conta.setNomeConta(rs.getString("nm_nome"));
//                conta.setSaldoConta(rs.getDouble("ds_saldo"));
//                conta.setDataDeposito(Timestamp.valueOf(rs.getTimestamp("ds_data_deposito").toLocalDateTime()));
//                contas.add(conta);
//            }
//            rs.close();
//
//            logger.info(Constantes.InfoBuscar + idUsuario);
//        } catch (SQLException e) {
//            logger.error(Constantes.ErroBuscarRegistroNoServidor);
//        }
//        return contas;
//    }
//
//    public void editarConta(ContaEntity conta){
//        logger.debug(Constantes.DebugEditarProcesso + conta);
//
//        String sql = "SELECT atualizar_conta(?,?,?)";
//
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, conta.getIdConta());
//            ps.setString(2, conta.getNomeConta());
//            ps.setString(3, conta.getTipoConta());
//
//            ps.execute();
//
//            logger.info(Constantes.InfoEditar + conta);
//        } catch (SQLException e){
//           logger.error(Constantes.ErroEditarRegistroNoServidor);
//        }
//    }
//
//    public void excluirConta(Integer idConta){
//        logger.debug(Constantes.DebugDeletarProcesso + idConta);
//
//        String sql = "SELECT excluir_conta(?)";
//
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//
//            ps.setInt(1, idConta);
//            ps.executeQuery();
//
//            logger.info(Constantes.InfoDeletar + idConta);
//        } catch (SQLException e){
//            logger.error(Constantes.ErroDeletarRegistroNoServidor);
//        }
//    }
//}
//
