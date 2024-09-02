package br.com.sistema.controle.financas.pessoais.service.usuario;

import br.com.sistema.controle.financas.pessoais.dao.usuario.UsuarioDao;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.security.PasswordSecurity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioService(UsuarioDao usuarioDao){
        this.usuarioDao = usuarioDao;
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

    public Integer obterIdUsuarioPorEmail(String email){
        return usuarioDao.obterIdUsuarioPorEmail(email);
    }
}
