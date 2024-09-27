package br.com.sistema.controle.financas.pessoais.dao.conta.impl;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.model.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SaldoDaoImpl implements SaldoDao {
    private static final Logger logger = LoggerFactory.getLogger(SaldoDaoImpl.class);

    public SaldoEntity inserirSaldo(SaldoEntity saldo){
        logger.debug(Constantes.DebugRegistroProcesso + saldo);

        String sql = "SElECT inserir_saldo(?,?,?)";

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, saldo.getIdUsuario());
            ps.setDouble(2, saldo.getSaldoAtual());
            ps.setTimestamp(3, saldo.getDataAtualizadaSaldo());

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int idSaldo = rs.getInt(1);
                saldo.setIdSaldo(idSaldo);
            }
            rs.close();

            logger.info(Constantes.InfoRegistrar + saldo);
        } catch (SQLException e){
           logger.error(Constantes.ErroRegistrarNoServidor);
        }
        return saldo;
    }

    public Double obterSaldoPorIdUsuario(Integer idUsuario) {
        logger.debug(Constantes.DebugBuscarProcesso + idUsuario);

        String sql = "SELECT obter_saldo_total(?)";

        double saldoTotal = 0.0;
        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                saldoTotal = rs.getDouble(1);
            }
            rs.close();

            logger.info(Constantes.InfoBuscar + idUsuario);
        } catch (SQLException e) {
            logger.error(Constantes.ErroBuscarRegistroNoServidor);
        }
        return saldoTotal;
    }
}
