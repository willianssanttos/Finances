package br.com.sistema.controle.financas.pessoais.port.output.usuario;

import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;

public interface IUsuarioDao {

    UsuarioEntity criarUsuario(UsuarioEntity usuario);
//    boolean verificarEmailExistente(String email);
//    UsuarioEntity validarLogin(String email);
}
