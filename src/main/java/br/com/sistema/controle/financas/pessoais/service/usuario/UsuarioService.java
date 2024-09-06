package br.com.sistema.controle.financas.pessoais.service.usuario;

import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.SaldoDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.dao.usuario.Impl.UsuarioDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.usuario.UsuarioDao;
import br.com.sistema.controle.financas.pessoais.model.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.security.PasswordSecurity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UsuarioService {

    private UsuarioDao usuarioDao;
    private SaldoDao saldoDao;

    public UsuarioService(){
        this.usuarioDao =  new UsuarioDaoImpl();
        this.saldoDao = new SaldoDaoImpl();
    }

    public UsuarioEntity criarUsuario(UsuarioEntity usuario) {

        usuario.getNomeUsuario();
        usuario.getEmailUsuario();
        usuario.setSenhaUsuario(PasswordSecurity.encriptarSenha(usuario.getSenhaUsuario()));
        usuario.getNumeroCelular();

        UsuarioEntity novoUsuario = usuarioDao.criarUsuario(usuario);

        if(novoUsuario.getIdUsuario() != null){
            SaldoEntity inserirSaldo = new SaldoEntity();
            inserirSaldo.setIdUsuario(novoUsuario.getIdUsuario());
            inserirSaldo.setSaldoAtual(0.00);
            inserirSaldo.setDataAtualizadaSaldo(Timestamp.valueOf(LocalDateTime.now()));

            saldoDao.inserirSaldo(inserirSaldo);
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
