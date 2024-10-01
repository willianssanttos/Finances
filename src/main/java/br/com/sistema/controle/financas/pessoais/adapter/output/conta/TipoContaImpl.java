//package br.com.sistema.controle.financas.pessoais.adapter.output.conta;
//
//import br.com.sistema.controle.financas.pessoais.port.output.conta.ITipoContaDao;
//import br.com.sistema.controle.financas.pessoais.utils.Constantes;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TipoContaImpl implements ITipoContaDao {
//    private static final Logger logger = LoggerFactory.getLogger(TipoContaImpl.class);
//    public List<String> obterTiposConta() {
//        logger.debug(Constantes.DebugBuscarProcesso);
//
//        String sql = "SELECT * FROM buscar_todos_tipos_conta()";
//        List<String> tiposConta = new ArrayList<>();
//
//        try (Connection conn = DataSourceConfig.getConexao();
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                tiposConta.add(rs.getString("nm_tipo_conta"));
//            }
//
//            logger.info(Constantes.InfoBuscar);
//        } catch (SQLException e) {
//            logger.error(Constantes.ErroBuscarRegistroNoServidor);
//        }
//        return tiposConta;
//    }
//}