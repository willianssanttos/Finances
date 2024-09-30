package br.com.sistema.controle.financas.pessoais.service.usuario;

import br.com.sistema.controle.financas.pessoais.dao.conta.impl.SaldoDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.conta.SaldoDao;
import br.com.sistema.controle.financas.pessoais.dao.usuario.impl.UsuarioDaoImpl;
import br.com.sistema.controle.financas.pessoais.dao.usuario.UsuarioDao;
import br.com.sistema.controle.financas.pessoais.model.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.security.PasswordSecurity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UsuarioService {
    private UsuarioDao usuarioDao;
    private SaldoDao saldoDao;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioService() {
        this.usuarioDao = new UsuarioDaoImpl();
        this.saldoDao = new SaldoDaoImpl();
    }

    public UsuarioEntity criarUsuario(UsuarioEntity usuario) {
        try {
            usuario.setSenhaUsuario(PasswordSecurity.encriptarSenha(usuario.getSenhaUsuario()));

            UsuarioEntity novoUsuario = usuarioDao.criarUsuario(usuario);

            if (novoUsuario.getIdUsuario() != null) {
                SaldoEntity inserirSaldo = new SaldoEntity();
                inserirSaldo.setIdUsuario(novoUsuario.getIdUsuario());
                inserirSaldo.setSaldoAtual(0.00);
                inserirSaldo.setDataAtualizadaSaldo(Timestamp.valueOf(LocalDateTime.now()));

                saldoDao.inserirSaldo(inserirSaldo);
            }
            return novoUsuario;
        } catch (Exception e){
            logger.error(Constantes.ErroCadastroUsuario, e);
        }
        return usuario;
    }

    public Boolean emailExiste(String email) {
        try {
            return usuarioDao.verificarEmailExistente(email);
        } catch (Exception e) {
            logger.error(Constantes.ErroVerificarEmail, e);
        }
        return emailExiste(email);
    }

    public UsuarioEntity autenticarUsuario(String email, String senha) {
        try {
            UsuarioEntity usuario = usuarioDao.validarLogin(email);
            boolean senhaValida = PasswordSecurity.checkSenha(senha, usuario.getSenhaUsuario());

            if (!senhaValida){
                return null;
            }
        } catch (Exception e){
            logger.error(Constantes.erroLoginConta, e);
        }
        return autenticarUsuario(email,senha);
    }
}
