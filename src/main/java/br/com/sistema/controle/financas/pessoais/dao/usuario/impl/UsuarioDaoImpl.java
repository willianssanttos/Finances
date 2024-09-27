package br.com.sistema.controle.financas.pessoais.dao.usuario.impl;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;
import br.com.sistema.controle.financas.pessoais.dao.conta.impl.TipoContaImpl;
import br.com.sistema.controle.financas.pessoais.dao.usuario.UsuarioDao;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDaoImpl implements UsuarioDao {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioDaoImpl.class);
    public UsuarioEntity criarUsuario(UsuarioEntity usuario){
        logger.debug(Constantes.DebugRegistroProcesso + usuario);

        String sql = "SELECT inserir_usuario(?,?,?,?)";

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNomeUsuario());
            ps.setString(2, usuario.getEmailUsuario());
            ps.setString(3, usuario.getSenhaUsuario());
            ps.setString(4, usuario.getNumeroCelular());

           ResultSet rs = ps.executeQuery();
           if (rs.next()){
               int idUsuario = rs.getInt(1);
               usuario.setIdUsuario(idUsuario);
           }
           rs.close();

           logger.info(Constantes.InfoRegistrar + usuario);
        } catch (SQLException e){
            logger.error(Constantes.ErroRegistrarNoServidor);
        }
        return usuario;
    }
    public boolean verificarEmailExistente(String email) {
        logger.debug(Constantes.ErroBuscarRegistroNoServidor + email);

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

            logger.info(Constantes.InfoBuscar + email);
        } catch (SQLException e) {
            logger.error(Constantes.ErroBuscarRegistroNoServidor);
        }
        return emailExiste;
    }

    public UsuarioEntity validarLogin(String email) {
        logger.debug(Constantes.DebugBuscarProcesso + email);

        String sql = "SELECT * FROM validar_login(?)";

       UsuarioEntity usuario = null;

        try (Connection conn = DataSourceConfig.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioEntity();

                usuario.setIdUsuario(rs.getInt("nr_id_usuario"));
                usuario.setEmailUsuario(rs.getString("ds_email"));
                usuario.setSenhaUsuario(rs.getString("ds_senha"));
            }
            rs.close();

            logger.info(Constantes.InfoBuscar + email);
        } catch (SQLException e) {
            logger.error(Constantes.ErroBuscarRegistroNoServidor);
        }
        return usuario;
    }
}
