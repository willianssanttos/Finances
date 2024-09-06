package br.com.sistema.controle.financas.pessoais.dao.conta.Impl;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;
import br.com.sistema.controle.financas.pessoais.dao.conta.TipoContaDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoContaImpl implements TipoContaDao {

    public String obterTipoConta(String tipoConta) {
        String sql = "SELECT buscar_tipo_conta_por_nome(?)";

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tipoConta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoConta;

    }
}
