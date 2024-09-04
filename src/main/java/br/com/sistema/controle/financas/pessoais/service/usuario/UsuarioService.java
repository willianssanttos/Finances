package br.com.sistema.controle.financas.pessoais.service.usuario;

import br.com.sistema.controle.financas.pessoais.dao.usuario.UsuarioDao;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.security.PasswordSecurity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

public class UsuarioService {

    private UsuarioDao usuarioDao;

    public UsuarioService(){
        this.usuarioDao =  new UsuarioDao();
    }

    public UsuarioEntity criarUsuario(UsuarioEntity usuario) {

        usuario.getNomeUsuario();
        usuario.getEmailUsuario();
        usuario.setSenhaUsuario(PasswordSecurity.encriptarSenha(usuario.getSenhaUsuario()));
        usuario.getNumeroCelular();

        return usuarioDao.criarUsuario(usuario);

    }

    public Boolean emailExiste(String email){
        return usuarioDao.verificarEmailExistente(email);
    }
    public Integer obterIdUsuarioPorEmail(String email){
        return usuarioDao.obterIdUsuarioPorEmail(email);
    }
}
