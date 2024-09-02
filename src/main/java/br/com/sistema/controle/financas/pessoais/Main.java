package br.com.sistema.controle.financas.pessoais;

import br.com.sistema.controle.financas.pessoais.configuration.DataSourceConfig;
import br.com.sistema.controle.financas.pessoais.dao.conta.ContaDao;
import br.com.sistema.controle.financas.pessoais.dao.usuario.UsuarioDao;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.service.conta.ContaService;
import br.com.sistema.controle.financas.pessoais.service.usuario.UsuarioService;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import br.com.sistema.controle.financas.pessoais.utils.FuncoesUtil;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarEmail;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNome;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNumeroCelular;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarSenha;
import static br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNumeroCelular.formatarNumeroCelular;

import java.time.LocalDateTime;
import java.util.Scanner;


public class Main {
    static DataSourceConfig config = new DataSourceConfig();
    static UsuarioDao usuarioDao = new UsuarioDao(config.jdbcTemplate(config.dataSource()));
    static UsuarioService usuarioService = new UsuarioService(usuarioDao);
    static ContaDao contaDao = new ContaDao(config.jdbcTemplate(config.dataSource()));
    static ContaService contaService = new ContaService(contaDao);

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Constantes.aberturaSistema();

        while (true) {
            System.out.println(Constantes.bemVindo);
            System.out.println("(1) Cadastrar Novo Usuario");
            System.out.println("(2) Fazer login");
            System.out.println("(3) Sair");

            String escolhaOpcao = input.nextLine();
            if (!FuncoesUtil.ehNumero(escolhaOpcao)) {
                System.err.println(Constantes.OpcaoInvalida);
                continue;
            }
            int escolha = Integer.parseInt(escolhaOpcao);

            switch (escolha) {
                case 1:
                    cadastrarNovoUsuario(input);
                    break;
                case 2:
                    realizarLogin(input);
                    break;
                case 3:
                    return;
                default:
                    System.err.println(Constantes.OpcaoInvalida);
            }
        }
    }

    public static void cadastrarNovoUsuario(Scanner input) {
        UsuarioEntity novoUsuario = new UsuarioEntity();

        do {
            String nomeUsuario = validarPrenchimentoEntrada(input,
                    "Digite o Nome Completo",
                    "Nome não preenchido");
            if (!ValidarNome.validarNome(nomeUsuario)) {
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
            if (!ValidarEmail.validaEmail(emailUsuario)) {
                System.err.println(Constantes.cadastroEmail);
                continue;
            }

            if (usuarioDao.verificarEmailExistente(emailUsuario)) {
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
            if (!ValidarNumeroCelular.validarNumeroCelular(numeroCelular)) {
                System.err.println(Constantes.cadastroCelular);
                continue;
            }
            novoUsuario.setNumeroCelular(formatarNumeroCelular(numeroCelular));
            break;
        } while (true);

        if (usuarioService.criarUsuario(novoUsuario) == null) {
            System.err.println(Constantes.ErroCadastroUsuario);
        }

        System.out.println(Constantes.CadastroRealizadoUsuario);
        System.out.println(Constantes.MensagemLoginUsuario);

    }

    private static void preencherSenha(Scanner input, UsuarioEntity novoUsuario) {

        String senha1;
        do {
            senha1 = validarPrenchimentoEntrada(input,
                    "Digite uma senha",
                    "Senha não preenchida");
            if (!ValidarSenha.isValidPassword(senha1)) {
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

    private static String validarPrenchimentoEntrada(Scanner input,
                                                     String mensagemDeEntrada,
                                                     String mesagemDeErro) {
        while (true) {
            System.out.println(mensagemDeEntrada);
            String valor = input.nextLine();

            if (valor.isBlank()) {
                System.out.println(mesagemDeErro);
                continue;
            }
            return valor;
        }
    }

    public static void usuarioLogado(Scanner input) {
        while (true) {

            System.out.println("(1) Adicionar conta");
            System.out.println("Escolha a opção desejada: ");

            String logadoUsuario = input.nextLine();
            if (!FuncoesUtil.ehNumero(logadoUsuario)) {
                System.err.println(Constantes.OpcaoInvalida);
                continue;
            }
            int Usuario = Integer.parseInt(logadoUsuario);

            switch (Usuario) {
                case 1:
                    realizarLogin(input);
                    break;
                case 2:
                    return;
                default:
                    System.out.println(Constantes.OpcaoInvalida);
            }
        }
    }

    public static void realizarLogin(Scanner input){

        System.out.println("----LOGIN---\n");
        System.out.println("Digite o E-MAIL: ");
        String email = input.nextLine();

        System.out.println("Digite a Senha: ");
        String senha = input.nextLine();

        Integer idUsuario = usuarioService.obterIdUsuarioPorEmail(email);

        if (idUsuario == null){
            System.err.println(Constantes.erroLoginConta);
            return;
        }

        System.out.println(Constantes.loginConta);

        cadastrarConta(input, idUsuario);
    }

    public static void cadastrarConta(Scanner input, Integer idUsuario) {
        ContaEntity novaConta = new ContaEntity();

        do {
            String nomeConta = validarPrenchimentoEntrada(input,
                    "Digite o Nome da Conta",
                    "Nome não preenchido");
            if (!ValidarNome.validarNome(nomeConta)){
                System.err.println(Constantes.cadastroNomeConta);
                continue;
            }
            novaConta.setNomeConta(nomeConta);
            break;
        } while (true);

        do {
            String saldo = validarPrenchimentoEntrada(input,
                    "Digite seu saldo atual",
                    "Saldo não preenchido");
            if (!FuncoesUtil.ehNumero(saldo)){
                System.err.println(Constantes.cadastroSaldo);
                continue;
            }
            novaConta.setSaldoConta(Double.parseDouble(saldo));
            break;
        }while (true);

        do {
            String tipoConta = validarPrenchimentoEntrada(input,
                    "Digite o Tipo da Conta",
                    "Tipo Conta não preenchido");
            if (!ValidarNome.validarNome(tipoConta)){
                System.err.println(Constantes.cadastroTipoConta);
                continue;
            }
            novaConta.setTipoConta(tipoConta);
            break;
        } while (true);

        novaConta.setIdUsuario(idUsuario);
        novaConta.setDataDeposito(LocalDateTime.now());

        if (contaService.criarConta(novaConta) == null){
            System.err.println(Constantes.ErroCadastroConta);
        }
        System.out.println(Constantes.cadastroConta);

    }

}