package br.com.sistema.controle.financas.pessoais.domain.command.usuario;

import br.com.sistema.controle.financas.pessoais.adapter.output.conta.SaldoDaoImpl;
import br.com.sistema.controle.financas.pessoais.port.input.usuario.IConta;
import br.com.sistema.controle.financas.pessoais.port.output.conta.ISaldoDao;
import br.com.sistema.controle.financas.pessoais.adapter.output.usuario.UsuarioDaoImpl;
import br.com.sistema.controle.financas.pessoais.port.output.usuario.IUsuarioDao;
import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.security.PasswordSecurity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UsuarioService implements IConta {
    private IUsuarioDao IUsuarioDao;
    private ISaldoDao ISaldoDao;
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public UsuarioService() {
        this.IUsuarioDao = new UsuarioDaoImpl();
        this.ISaldoDao = new SaldoDaoImpl();
    }

    public UsuarioEntity criarUsuario(UsuarioEntity usuario) {
        try {
            usuario.setSenhaUsuario(PasswordSecurity.encriptarSenha(usuario.getSenhaUsuario()));

            UsuarioEntity novoUsuario = IUsuarioDao.criarUsuario(usuario);

            if (novoUsuario.getIdUsuario() != null) {
                SaldoEntity inserirSaldo = new SaldoEntity();
                inserirSaldo.setIdUsuario(novoUsuario.getIdUsuario());
                inserirSaldo.setSaldoAtual(0.00);
                inserirSaldo.setDataAtualizadaSaldo(Timestamp.valueOf(LocalDateTime.now()));

                ISaldoDao.inserirSaldo(inserirSaldo);
            }
            return novoUsuario;
        } catch (Exception e){
            logger.error(Constantes.ErroCadastroUsuario, e);
        }
        return usuario;
    }

//    public Boolean emailExiste(String email) {
//        try {
//            return IUsuarioDao.verificarEmailExistente(email);
//        } catch (Exception e) {
//            logger.error(Constantes.ErroVerificarEmail, e);
//        }
//        return emailExiste(email);
//    }
//
//    public UsuarioEntity autenticarUsuario(String email, String senha) {
//        try {
//            UsuarioEntity usuario = IUsuarioDao.validarLogin(email);
//            boolean senhaValida = PasswordSecurity.checkSenha(senha, usuario.getSenhaUsuario());
//
//            if (!senhaValida){
//                return null;
//            }
//        } catch (Exception e){
//            logger.error(Constantes.erroLoginConta, e);
//        }
//        return autenticarUsuario(email,senha);
//    }
}
