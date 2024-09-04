package br.com.sistema.controle.financas.pessoais.dao.conta;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;

import java.sql.*;

public class SaldoDao {

    public int inserirSaldo(int idUsuario, double saldoAtual, Timestamp dataAtualizacao){
        String sql = "SElECT inserir_saldo(?,?,?)";

        int idSaldo = -1;
        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ps.setDouble(2, saldoAtual);
            ps.setTimestamp(3, dataAtualizacao);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                idSaldo = rs.getInt(1);
            }
            rs.close();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return idSaldo;
    }

    public Integer obterSaldoPorIdConta(Integer idConta) {
        String sql = "SELECT obter_saldo(?)";

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idConta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idConta = rs.getInt(1);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idConta;
    }
}
