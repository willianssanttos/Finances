//package br.com.sistema.controle.financas.pessoais.facade;
//
//import br.com.sistema.controle.financas.pessoais.domain.exception.ServiceException;
//import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ContaEntity;
//import br.com.sistema.controle.financas.pessoais.domain.entity.conta.ExtratoEntity;
//import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
//import br.com.sistema.controle.financas.pessoais.domain.command.conta.ContaService;
//import br.com.sistema.controle.financas.pessoais.domain.command.conta.TransacaoService;
//import br.com.sistema.controle.financas.pessoais.domain.command.usuario.UsuarioService;
//import br.com.sistema.controle.financas.pessoais.utils.Constantes;
//
//import java.util.List;
//
//public class FacadeService {
//
//    /**
//     * FacadeService atua como uma fachada para encapsular a lógica das operações
//     * de usuário, contas e transações. Fornece uma interface simples para a
//     * manipulação dessas entidades, facilitando a chamada dos serviços.
//     */
//
//    private static FacadeService instance;
//    private final UsuarioService usuarioService;
//    private final ContaService contaService;
//    private final TransacaoService transacaoService;
//
//    //Construtor (Padrão Singleton)
//    private FacadeService(){
//        this.usuarioService = new UsuarioService();
//        this.contaService = new ContaService();
//        this.transacaoService = new TransacaoService();
//    }
//
//    //Metodo para garantir que a instãncia seja unica(Padrão Singleton)
//    public static synchronized FacadeService getInstance(){
//        if (instance == null){
//            instance = new FacadeService();
//        }
//        return instance;
//    }
//
//    public void criarUsuario(UsuarioEntity usuario) {
//        try {
//            usuarioService.criarUsuario(usuario);
//        } catch (Exception e) {
//            throw new ServiceException(Constantes.ErroCadastroUsuario, e);
//        }
//    }
//
//    public UsuarioEntity autenticarUsuario(String email, String senha) {
//        try {
//            return usuarioService.autenticarUsuario(email, senha);
//        } catch (Exception e) {
//            throw new ServiceException(Constantes.erroLoginConta, e);
//        }
//    }
//
//    public Boolean emailExiste(String email) {
//        try {
//            return usuarioService.emailExiste(email);
//        } catch (Exception e) {
//            throw new ServiceException(Constantes.ErroVerificarEmail, e);
//        }
//    }
//
//
//    public List<String> obterTiposConta() {
//        try {
//            return contaService.obterTodosTiposConta();
//        } catch (Exception e) {
//            throw new ServiceException(Constantes.cadastroTipoConta, e);
//        }
//    }
//
//
//    public void criarConta(ContaEntity conta){
//        try {
//            contaService.criarConta(conta);
//        } catch (ServiceException e){
//            throw new ServiceException(Constantes.ErroCadastroConta, e);
//        }
//    }
//
//    public void excluirConta(Integer idConta){
//        try {
//            contaService.excluirConta(idConta);
//        } catch (Exception e){
//            throw new ServiceException(Constantes.ErroExcluir, e);
//        }
//    }
//
//    public void editarConta(ContaEntity conta){
//        try {
//            contaService.editarConta(conta);
//        } catch (Exception e){
//            throw new ServiceException(Constantes.ErroEditar, e);
//        }
//    }
//
//    public void realizarTransacao(Integer idConta, Integer idSaldo, String descricao, String categoria, Double valor, int tipo){
//        try {
//            transacaoService.registrarTransacao(idConta, idSaldo, descricao, categoria, valor, tipo);
//        } catch (Exception e){
//            throw new ServiceException(Constantes.ErrocadastroTransacao, e);
//        }
//    }
//
//    public Double obterSaldoTotal(Integer idUsuario){
//        try {
//            return contaService.obterSaldo(idUsuario);
//        } catch (Exception e){
//            throw new ServiceException(Constantes.ErrorRecuperarSaldo, e);
//        }
//    }
//
//    public List<ContaEntity> obterContasPorUsuario(Integer idUsuario){
//        try {
//            return contaService.obterContasPorIdUsuario(idUsuario);
//        } catch (Exception e){
//            throw new ServiceException(Constantes.ErrorRecuperarContas, e);
//        }
//    }
//
//    public List<ExtratoEntity> obterExtratoPorMes(Integer idUsuario, int mes, int ano){
//        try {
//            return transacaoService.obterExtratoPorMes(idUsuario, mes, ano);
//        } catch (Exception e){
//            throw new ServiceException(Constantes.ErrorRecuperarExtrato, e);
//        }
//    }
//}
