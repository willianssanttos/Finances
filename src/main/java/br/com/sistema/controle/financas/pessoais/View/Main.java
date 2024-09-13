package br.com.sistema.controle.financas.pessoais.View;

import br.com.sistema.controle.financas.pessoais.facade.FacadeService;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.model.conta.ExtratoEntity;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.service.usuario.UsuarioService;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import br.com.sistema.controle.financas.pessoais.utils.FuncoesUtil;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarEmail;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNome;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNumeroCelular;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarSenha;
import static br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNumeroCelular.formatarNumeroCelular;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {

    static UsuarioService usuarioService = new UsuarioService();
    static FacadeService facadeService = FacadeService.getInstance();

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

    public static void realizarLogin(Scanner input){
        while (true){
            System.out.println("----LOGIN----\n");

            System.out.println("Digite o E-MAIL: ");
            String email = input.nextLine();

            System.out.println("Digite a Senha: ");
            String senha = input.nextLine();

            UsuarioEntity usuario = usuarioService.autenticarUsuario(email, senha);

            if (usuario == null){
                System.err.println(Constantes.erroLoginConta);
            } else {
                System.out.println(Constantes.loginConta);
                usuarioLogado(input, usuario.getIdUsuario());
                break;
            }
        }
    }

    public static void usuarioLogado(Scanner input, Integer idUsuario) {
        while (true) {
            Double saldoAtual = facadeService.obterSaldoTotal(idUsuario);
            System.out.println("\nSEU SALDO TOTAL R$: " + saldoAtual);

            System.out.println("\nSUAS CONTAS");

            List<ContaEntity> contas = facadeService.obterContasPorUsuario(idUsuario);

            if (contas == null){
                System.out.println(Constantes.criarConta);
                continue;
            }

            for (int i = 0; i < contas.size(); i++){
                ContaEntity conta = contas.get(i);
                System.out.println("Conta: " + conta.getNomeConta() +
                        " | Saldo R$ " + conta.getSaldoConta());
            }

            System.out.println("\n(1) Adicionar Conta");
            System.out.println("(2) Realizar Transação");
            //System.out.println("(3) Minha Contas");
            System.out.println("(3) Extratos");
            System.out.println("(4) Deslogar");

            String logadoUsuario = input.nextLine();
            if (!FuncoesUtil.ehNumero(logadoUsuario)) {
                System.err.println(Constantes.OpcaoInvalida);
                continue;
            }
            int opcaoEscolha = Integer.parseInt(logadoUsuario);

            switch (opcaoEscolha) {
                case 1:
                    cadastrarConta(input, idUsuario, idUsuario);
                    break;
                case 2:
                    registrarTransacao(input, idUsuario);
                    break;
//                case 3:
//                    break;
                case 3:
                    mostrarExtrato(input, idUsuario);
                    break;
                case 4:
                    return;
                default:
                    System.out.println(Constantes.OpcaoInvalida);
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

            Boolean emailExiste = usuarioService.emailExiste(emailUsuario);
            if (emailExiste == true) {
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

        facadeService.criarUsuario(novoUsuario);
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
                System.err.println(Constantes.confirmacaoSenha);
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

    public static void cadastrarConta(Scanner input, Integer idUsuario, Integer idSaldo) {
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
        novaConta.setIdSaldo(idSaldo);
        novaConta.setDataDeposito(Timestamp.valueOf(LocalDateTime.now()));

        facadeService.criarConta(novaConta);
        System.out.println(Constantes.cadastroConta);
    }

   public static void registrarTransacao(Scanner input, Integer idUsuario) {
        List<ContaEntity> contas = facadeService.obterContasPorUsuario(idUsuario);

        if (contas.isEmpty()){
            System.err.println(Constantes.contaNaoEncontrada);
            return;
        }

        System.out.println("Selecione a conta para realizar a transação: ");
        for (int i = 0; i < contas.size(); i++){
            ContaEntity conta = contas.get(i);
            System.out.println((i + 1) + ". Conta: " + conta.getNomeConta() +
                    " | Tipo conta: " + conta.getTipoConta() +
                    " | Saldo R$ " + conta.getSaldoConta());
        }

        int escolha;
        while (true){
            System.out.println("\nDigite o número da conta:");
            String escolhaStr = input.nextLine().trim();

            if (!FuncoesUtil.ehNumero(escolhaStr)){
                System.out.println("Opção invalida! Digite o numero correspondente a conta");
                continue;
            }

            escolha = Integer.parseInt(escolhaStr);
            if (escolha < 1 || escolha > contas.size()){
                System.err.println("Opção invalida.");
                return;
            }else {
                break;
            }
        }

        ContaEntity contaSelecionada = contas.get(escolha - 1);

        System.out.println("Digite a descrição da transação:");
        String descricao = input.nextLine();

        System.out.println("Digite a categoria:");
        String categoria = input.nextLine();

        Double valor;
        while (true){
            System.out.println("Digite o valor da transação:");
            String valorStr = input.nextLine().trim();
            if (!FuncoesUtil.ehNumero(valorStr)){
                System.out.println("Digite so numeros.");
            } else {
                valor = Double.parseDouble(valorStr);
                break;
            }
        }

        int tipo;
        while (true){
            System.out.println("Digite o tipo de transação (1 para receitas, 2 para despesas):");
            String tipoStr = input.nextLine().trim();

            if(!FuncoesUtil.ehNumero(tipoStr)){
                System.out.println(Constantes.tipoTransacao);
            } else {
                tipo = Integer.parseInt(tipoStr);
                if (tipo !=1 && tipo != 2){
                    System.err.println(Constantes.tipoTransacao);
                    return;
                } else {
                    break;
                }
            }
        }

        facadeService.realizarTransacao(contaSelecionada.getIdConta(), contaSelecionada.getIdSaldo(), descricao, categoria, valor, tipo);
        System.out.println(Constantes.cadastroTransacao);

       while (true){
           System.out.println("\nDeseja sair para o menu inicial? (s/n):");
           String saida = input.nextLine().trim().toLowerCase();

           if (saida.equals("s")){
               return;
           } else if (saida.equals("n")){
               registrarTransacao(input,idUsuario);
               break;
           } else {
               System.out.println(Constantes.erroSN + " para realizar nova transação.");
           }
       }
   }
    private static void mostrarExtrato(Scanner input, Integer idUsuario){
        while (true){
            //Pega o mês e o ano corrente por padrão
            LocalDate hoje = LocalDate.now();
            int mes = hoje.getMonthValue();
            int ano = hoje.getYear();

            System.out.println("\nDeseja filtrar por um período especifico? (s/n)");
            String resposta = input.nextLine();

            while (!resposta.equals("s") && !resposta.equals("n")){
                System.out.println("Opção invalida! Digite 's' para filtrar ou 'n' para mostrar o extrato completo");
                resposta = input.nextLine().trim().toLowerCase();
            }

            //Se o usuário quiser um periodo especifico, solicitar o mês e o ano
            if (resposta.equalsIgnoreCase("s")){
                System.out.println("Digite o mês (1-12):");
                mes = Integer.parseInt(input.nextLine());

                System.out.println("Digite o ano:");
                ano = Integer.parseInt(input.nextLine());
            }

            List<ExtratoEntity> extratos = facadeService.obterExtratoPorMes(idUsuario, mes, ano);

            if (extratos.isEmpty()){
                System.out.println("Nenhuma transação registrada!");
                return;
            }

            double totalGastos = 0;
            double totalGanhos = 0;

            // Formata a data
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.println("\nExtrato de Transações:");
            for (ExtratoEntity extrato : extratos){
                // Formata a data para dia/mês/ano
                String dataFormatada = extrato.getDataMovimentacao().toLocalDateTime().toLocalDate().format(formatter);
                String tipo = extrato.getTipoTransacao().equals("Ganho") ? "+" : "-";

                // Exibe a transação com data e tipo de conta
                System.out.println("Data: " + dataFormatada +"\n"+
                        "| Conta: " + extrato.getNomeConta() +
                        "| Tipo: " + extrato.getTipoConta() +
                        "| Categoria: " + extrato.getCategoria() +
                        "| Valor: " + tipo + extrato.getValor());

                if (extrato.getTipoTransacao().equals("Ganho")){
                    totalGanhos += extrato.getValor();
                } else {
                    totalGastos += extrato.getValor();
                }
            }
            System.out.println("\nExtrato do mês:");
            System.out.printf("Ganhos: R$ %.2f | Gastos: R$ %.2f\n", totalGanhos, totalGastos);

            while (true){
                System.out.println("\nDeseja sair para o menu inicial? (s/n):");
                String saida = input.nextLine().trim().toLowerCase();

                if (saida.equals("s")){
                    return;
                } else if (saida.equals("n")){
                    break;
                } else {
                    System.out.println(Constantes.erroSN + " para permanecer no extrato.");
                }
            }
        }
    }

    // todo trigger que realiza o roolback da operação caso a mesma der algum erro
    // todo realizar a implementação do cartao de credito
}