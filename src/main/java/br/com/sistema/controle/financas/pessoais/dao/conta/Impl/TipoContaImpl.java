package br.com.sistema.controle.financas.pessoais.dao.conta.Impl;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;
import br.com.sistema.controle.financas.pessoais.dao.conta.TipoContaDao;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.model.conta.TipoContaEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoContaImpl implements TipoContaDao {

    public List<String> obterTiposConta() {
        String sql = "SELECT * FROM buscar_todos_tipos_conta()";
        List<String> tiposConta = new ArrayList<>();

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                tiposConta.add(rs.getString("nm_tipo_conta"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposConta;
    }
}