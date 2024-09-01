package br.com.sistema.controle.financas.pessoais;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;
import br.com.sistema.controle.financas.pessoais.dao.usuario.UsuarioDao;
import br.com.sistema.controle.financas.pessoais.model.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.service.usuario.UsuarioService;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import br.com.sistema.controle.financas.pessoais.utils.FuncoesUtil;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarEmail;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNome;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNumeroCelular;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarSenha;

import java.util.Scanner;

import static br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNumeroCelular.formatarNumeroCelular;

public class Main {
    static DataSourceConfig config = new DataSourceConfig();
    static UsuarioDao usuarioDao = new UsuarioDao(config.jdbcTemplate(config.dataSource()));
    static UsuarioService usuarioService = new UsuarioService(usuarioDao);
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Constantes.aberturaSistema();

        while (true){
            System.out.println(Constantes.bemVindo);
            System.out.println("(1) Cadastrar Novo Usuario");
            System.out.println("(2) Fazer login");
            System.out.println("(3) Sair");

            String escolhaOpcao = input.nextLine();
            if (!FuncoesUtil.ehNumero(escolhaOpcao)){
                System.err.println(Constantes.OpcaoInvalida);
                continue;
            }
            int escolha = Integer.parseInt(escolhaOpcao);

            switch (escolha){
                case 1:
                    cadastrarNovoUsuario(input);
                    break;
                case 2:
                   // realizarLogin(input);
                    break;
                case 3:
                    return;
                default:
                    System.err.println(Constantes.OpcaoInvalida);
            }
        }
    }

    public static void cadastrarNovoUsuario(Scanner input){
        UsuarioEntity novoUsuario = new UsuarioEntity();

        do {
            String nomeUsuario = validarPrenchimentoEntrada(input,
                    "Digite o Nome Completo",
                    "Nome não preenchido");
            if (!ValidarNome.validarNomeCliente(nomeUsuario)){
                System.err.println(Constantes.cadastroNome);
                continue;
            }
            novoUsuario.setNomeUsuario(nomeUsuario);
            break;
        } while (true);

        do {
            String emailUsuario = validarPrenchimentoEntrada(input,
                    "Digite o E-MAIL",
                    "E-MAIL não preenchido");
            if (!ValidarEmail.validaEmail(emailUsuario)){
                System.err.println(Constantes.cadastroEmail);
                continue;
            }

            if (usuarioDao.verificarEmailExistente(emailUsuario)){
                System.err.println(Constantes.EmailJaCadastrado);
                continue;
            }

            novoUsuario.setEmailUsuario(emailUsuario);
            break;
        } while (true);

        preencherSenha(input, novoUsuario);

        do {
            String numeroCelular = validarPrenchimentoEntrada(input,
                    "Digite o Número Celular",
                    "Número não preenchido");
            if (!ValidarNumeroCelular.validarNumeroCelular(numeroCelular)){
                System.err.println(Constantes.cadastroCelular);
                continue;
            }
            novoUsuario.setNumeroCelular(formatarNumeroCelular(numeroCelular));
            break;
        } while (true);

        if (usuarioService.criarUsuario(novoUsuario) == null){
            System.out.println(Constantes.ErroCadastroUsuario);
        }

        System.out.println(Constantes.CadastroRealizadoUsuario);
        System.out.println(Constantes.MensagemLoginUsuario);

    }

    private static String validarPrenchimentoEntrada(Scanner input,
                                                     String mensagemDeEntrada,
                                                     String mesagemDeErro) {
        while (true){
            System.out.println(mensagemDeEntrada);
            String valor = input.nextLine();

            if (valor.isBlank()){
                System.out.println(mesagemDeErro);
                continue;
            }
            return valor;
        }
    }

    private static void preencherSenha(Scanner input, UsuarioEntity novoUsuario){

        String senha1;
        do {
            senha1 = validarPrenchimentoEntrada(input,
                    "Digite uma senha",
                    "Senha não preenchida");
            if (!ValidarSenha.isValidPassword(senha1)){
                System.err.println(Constantes.cadastroSenha);
                continue;
            }
            novoUsuario.setSenhaUsuario(senha1);
            break;
        } while (true);

        do {
            String senha2 = validarPrenchimentoEntrada(input,
                    "Confirme novamente a senha",
                    "Senha não preenchida");
            if (!senha1.equals(senha2)) {
                System.err.println("Senha incorreta! Verifique a senha digitada.");
                continue;
            }
            novoUsuario.setSenhaUsuario(senha1);
            break;
        } while (true);
    }
}