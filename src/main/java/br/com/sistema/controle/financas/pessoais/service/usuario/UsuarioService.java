package br.com.sistema.controle.financas.pessoais.service.usuario;

import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.dao.usuario.UsuarioDao;
import br.com.sistema.controle.financas.pessoais.model.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.security.PasswordSecurity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UsuarioService {

    private UsuarioDao usuarioDao;
    private SaldoDao saldoDao;

    public UsuarioService(){
        this.usuarioDao =  new UsuarioDao();
        this.saldoDao = new SaldoDao();
    }

    public UsuarioEntity criarUsuario(UsuarioEntity usuario) {

        usuario.getNomeUsuario();
        usuario.getEmailUsuario();
        usuario.setSenhaUsuario(PasswordSecurity.encriptarSenha(usuario.getSenhaUsuario()));
        usuario.getNumeroCelular();

        UsuarioEntity novoUsuario = usuarioDao.criarUsuario(usuario);

        usuario.setSaldoAtual(0.00);
        usuario.setDataAtualizadaSaldo(Timestamp.valueOf(LocalDateTime.now()));

        if(novoUsuario.getIdUsuario() != null){
            saldoDao.inserirSaldo(
                    novoUsuario.getIdUsuario(),
                    novoUsuario.getSaldoAtual(),
                    novoUsuario.getDataAtualizadaSaldo());
        }
        return novoUsuario;
    }

    public Boolean emailExiste(String email){
        return usuarioDao.verificarEmailExistente(email);
    }
    public Integer obterIdUsuarioPorEmail(String email){
        return usuarioDao.obterIdUsuarioPorEmail(email);
    }
}
