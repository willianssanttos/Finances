package br.com.sistema.controle.financas.pessoais.dao.usuario;

import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import org.springframework.jdbc.core.JdbcTemplate;

public class UsuarioDao {

    private final JdbcTemplate jdbcTemplate;

    public UsuarioDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public UsuarioEntity criarUsuario(UsuarioEntity usuario){
        String sql = "SELECT inserir_usuario(?,?,?,?)";
        jdbcTemplate.queryForObject(sql, new Object[]{
                        usuario.getNomeUsuario(), usuario.getEmailUsuario(), usuario.getSenhaUsuario(), usuario.getNumeroCelular() },
                Integer.class);
        return usuario;
    }

    public boolean verificarEmailExistente(String email){
        String sql = "SELECT verificar_email_existente(?)";
        return jdbcTemplate.queryForObject(sql, Boolean.class, email);
    }

    public Integer obterIdUsuarioPorEmail(String email){
        String sql = "SELECT recuperar_id_usuario_por_email(?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, Integer.class);
    }
}
