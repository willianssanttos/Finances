package br.com.sistema.controle.financas.pessoais.dao.conta.Impl;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;
import br.com.sistema.controle.financas.pessoais.dao.conta.TransacaoContaDao;
import br.com.sistema.controle.financas.pessoais.model.conta.TransacoesContaEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransacaoContaDaoImpl implements TransacaoContaDao {
    public void inserirTransacao(TransacoesContaEntity transacao){
        String sql = "SELECT inserir_transacao(?,?,?,?,?,?)";
        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, transacao.getIdSaldo());
            ps.setInt(2, transacao.getIdConta());
            ps.setString(3, transacao.getDescricao());
            ps.setDouble(4, transacao.getValor());
            ps.setTimestamp(5, transacao.getDataMovimentacao());
            ps.setInt(6, transacao.getTipo());

            ps.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
