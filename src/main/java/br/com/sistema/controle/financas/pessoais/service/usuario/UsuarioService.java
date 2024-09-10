package br.com.sistema.controle.financas.pessoais.service.usuario;

import br.com.sistema.controle.financas.pessoais.dao.conta.Impl.SaldoDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.dao.usuario.Impl.UsuarioDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.usuario.UsuarioDao;
import br.com.sistema.controle.financas.pessoais.handler.ServiceException;
import br.com.sistema.controle.financas.pessoais.model.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.security.PasswordSecurity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UsuarioService {

    private UsuarioDao usuarioDao;
    private SaldoDao saldoDao;

    public UsuarioService() {
        this.usuarioDao = new UsuarioDaoImpl();
        this.saldoDao = new SaldoDaoImpl();
    }

    public UsuarioEntity criarUsuario(UsuarioEntity usuario) {
        try {
            usuario.getNomeUsuario();
            usuario.getEmailUsuario();
            usuario.setSenhaUsuario(PasswordSecurity.encriptarSenha(usuario.getSenhaUsuario()));
            usuario.getNumeroCelular();

            UsuarioEntity novoUsuario = usuarioDao.criarUsuario(usuario);

            if (novoUsuario.getIdUsuario() != null) {
                SaldoEntity inserirSaldo = new SaldoEntity();
                inserirSaldo.setIdUsuario(novoUsuario.getIdUsuario());
                inserirSaldo.setSaldoAtual(0.00);
                inserirSaldo.setDataAtualizadaSaldo(Timestamp.valueOf(LocalDateTime.now()));

                saldoDao.inserirSaldo(inserirSaldo);
            }
            return novoUsuario;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(Constantes.ErroCadastroConta, e);
        }
    }

    public Boolean emailExiste(String email) {
        try {
            return usuarioDao.verificarEmailExistente(email);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(Constantes.ErroVerificarEmail, e);
        }
    }

    public UsuarioEntity autenticarUsuario(String email, String senha) {
        try {
            UsuarioEntity usuario = usuarioDao.validarLogin(email);

            if (usuario == null) {
                throw new IllegalArgumentException(Constantes.usuarioNaoEncontrado);
            }

            boolean senhaValida = PasswordSecurity.checkSenha(senha, usuario.getSenhaUsuario());
            if (!senhaValida) {
                throw new IllegalArgumentException(Constantes.erroLoginConta);
            }
            return usuario;
        } catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(Constantes.erroLoginConta, e);
        }
    }

    public Integer obterIdUsuarioPorEmail(String email){
        try {
            return usuarioDao.obterIdUsuarioPorEmail(email);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
