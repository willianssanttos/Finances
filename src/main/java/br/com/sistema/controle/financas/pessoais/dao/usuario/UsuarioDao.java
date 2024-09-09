package br.com.sistema.controle.financas.pessoais.dao.usuario;

import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;

public interface UsuarioDao {

    UsuarioEntity criarUsuario(UsuarioEntity usuario);
    boolean verificarEmailExistente(String email);
    boolean validarLogin(String email, String senha);
    Integer obterIdUsuarioPorEmail(String email);
}
