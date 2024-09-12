package br.com.sistema.controle.financas.pessoais.facade;

import br.com.sistema.controle.financas.pessoais.handler.ServiceException;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.service.conta.ContaService;
import br.com.sistema.controle.financas.pessoais.service.conta.TransacaoService;
import br.com.sistema.controle.financas.pessoais.service.usuario.UsuarioService;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;

import java.util.List;

public class FacadeService {

    private static FacadeService instance;
    private final UsuarioService usuarioService;
    private final ContaService contaService;
    private final TransacaoService transacaoService;

    //Construtor (Padrão Singleton)
    private FacadeService(){
        this.usuarioService = new UsuarioService();
        this.contaService = new ContaService();
        this.transacaoService = new TransacaoService();
    }

    //Metodo para garantir que a instãncia seja unica(Padrão Singleton)
    public static synchronized FacadeService getInstance(){
        if (instance == null){
            instance = new FacadeService();
        }
        return instance;
    }

    //Metodo de criação de conta com a logica encapsulada
    public void criarUsuario(UsuarioEntity usuario) {
        try {
            usuarioService.criarUsuario(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(Constantes.ErroCadastroConta, e);
        }
    }

    //Metodo de criação de conta com a logica encapsulada
    public void criarConta(ContaEntity conta){
        try {
            // Lógica para criação de uma conta
            contaService.criarConta(conta);
        } catch (ServiceException e){
            e.printStackTrace();
            throw new ServiceException(Constantes.ErroCadastroConta, e);
        }
    }

    //Metodo para realizar transações com a logica centralizada
    public void realizarTransacao(Integer idConta, Integer idSaldo, String descricao, Double valor, int tipo){
        try {
            // Lógica para realizar transação
            transacaoService.registrarTransacao(idConta, idSaldo, descricao, valor, tipo);
        } catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(Constantes.ErrocadastroTransacao, e);
        }
    }

    public Double obterSaldoTotal(Integer idUsuario){
        try {
            return contaService.obterSaldo(idUsuario);
        } catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(Constantes.ErrorRecuperarIdUsuario, e);
        }
    }

    public List<ContaEntity> obterContasPorUsuario(Integer idUsuario){
        try {
            return contaService.obterContasPorIdUsuario(idUsuario);
        } catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(Constantes.ErrorRecuperarContas, e);
        }
    }

}
