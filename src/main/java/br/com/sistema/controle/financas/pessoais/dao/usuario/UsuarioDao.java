package br.com.sistema.controle.financas.pessoais.dao.usuario;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {

    public UsuarioEntity criarUsuario(UsuarioEntity usuario){
        String sql = "SElECT inserir_usuario(?,?,?,?)";

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getEmailUsuario());
            ps.setString(3, usuario.getSenhaUsuario());
            ps.setString(4, usuario.getNumeroCelular());

            ps.execute();

        } catch (SQLException e){
            e.printStackTrace();
        }
        return usuario;
    }
    public boolean verificarEmailExistente(String email) {
        String sql = "SELECT verificar_email_existente(?)";
        boolean emailExiste = false;

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                emailExiste = rs.getBoolean(1);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emailExiste;
    }

    public Integer obterIdUsuarioPorEmail(String email) {
        String sql = "SELECT recuperar_id_usuario_por_email(?)";
        Integer idUsuario = null;

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idUsuario = rs.getInt(1);
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idUsuario;
    }
}
