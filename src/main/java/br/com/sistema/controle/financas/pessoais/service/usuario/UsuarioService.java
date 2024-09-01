package br.com.sistema.controle.financas.pessoais.service.usuario;

import br.com.sistema.controle.financas.pessoais.dao.usuario.UsuarioDao;
import br.com.sistema.controle.financas.pessoais.model.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.security.PasswordSecurity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class UsuarioService {

    private UsuarioDao usuarioDao;

    private PasswordSecurity passwordSecurity;

    public UsuarioService(UsuarioDao usuarioDao){
        this.usuarioDao = usuarioDao;
    }

    public UsuarioService(PasswordSecurity passwordSecurity){
        this.passwordSecurity = passwordSecurity;
    }

    public UsuarioEntity criarUsuario(UsuarioEntity usuario) {

        Boolean emailExiste = usuarioDao.verificarEmailExistente(usuario.getEmailUsuario());
        if(emailExiste != null && emailExiste){
            System.err.println(Constantes.EmailJaCadastrado);
        }

        usuario.getNomeUsuario();
        usuario.getEmailUsuario();
        usuario.setSenhaUsuario(PasswordSecurity.encriptarSenha(usuario.getSenhaUsuario()));
        usuario.getNumeroCelular();

        return usuarioDao.criarUsuario(usuario);

    }




}
