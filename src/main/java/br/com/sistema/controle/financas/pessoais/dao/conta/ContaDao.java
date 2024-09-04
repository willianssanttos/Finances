package br.com.sistema.controle.financas.pessoais.dao.conta;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDao {

    public ContaEntity criarConta(ContaEntity conta){
        String sql = "SElECT inserir_conta(?,?,?,?,?)";

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, conta.getIdUsuario());
            ps.setInt(2, conta.getIdSaldo());
            ps.setString(3, conta.getNomeConta());
            ps.setDouble(4, conta.getSaldoConta());
            ps.setString(5, conta.getTipoConta());
            ps.setTimestamp(6, conta.getDataDeposito());

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int idConta = rs.getInt(1);
                conta.setIdConta(idConta);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return conta;
    }

    public List<ContaEntity> obterContasPorUsuario(Integer idUsuario) {
        String sql = "SELECT * FROM buscar_contas_por_usuario(?)";
        List<ContaEntity> contas = new ArrayList<>();

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ContaEntity conta = new ContaEntity();
                conta.setIdConta(rs.getInt("nr_id_conta"));
                conta.setIdSaldo(rs.getInt("fk_nr_id_saldo"));
                conta.setNomeConta(rs.getString("nm_nome"));
                conta.setSaldoConta(rs.getDouble("ds_saldo"));
                conta.setDataDeposito(Timestamp.valueOf(rs.getTimestamp("ds_data_deposito").toLocalDateTime()));
                contas.add(conta);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contas;
    }
}

