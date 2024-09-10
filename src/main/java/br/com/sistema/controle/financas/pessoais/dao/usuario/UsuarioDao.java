package br.com.sistema.controle.financas.pessoais.dao.usuario;

import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;

public interface UsuarioDao {

    UsuarioEntity criarUsuario(UsuarioEntity usuario);
    boolean verificarEmailExistente(String email);
    public UsuarioEntity validarLogin(String email);
    Integer obterIdUsuarioPorEmail(String email);
}
