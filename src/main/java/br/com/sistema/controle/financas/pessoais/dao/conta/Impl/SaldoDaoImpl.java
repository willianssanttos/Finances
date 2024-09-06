package br.com.sistema.controle.financas.pessoais.dao.conta.Impl;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.model.conta.SaldoEntity;

import java.sql.*;

public class SaldoDaoImpl implements SaldoDao {

    public SaldoEntity inserirSaldo(SaldoEntity saldo){
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

        } catch (SQLException e){
            e.printStackTrace();
        }

        return saldo;
    }

    public Double obterSaldoPorIdUsuario(Integer idUsuario) {
        String sql = "SELECT obter_saldo_total(?)";

        Double saldoTotal = 0.0;
        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                saldoTotal = rs.getDouble(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return saldoTotal;
    }
}
