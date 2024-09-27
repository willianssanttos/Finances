package br.com.sistema.controle.financas.pessoais.view;

import br.com.sistema.controle.financas.pessoais.Enum.CategoriaEnum;
import br.com.sistema.controle.financas.pessoais.dao.usuario.impl.UsuarioDaoImpl;
import br.com.sistema.controle.financas.pessoais.facade.FacadeService;
import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import br.com.sistema.controle.financas.pessoais.model.conta.ExtratoEntity;
import br.com.sistema.controle.financas.pessoais.model.conta.TotaisGanhosGastosEntity;
import br.com.sistema.controle.financas.pessoais.model.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import br.com.sistema.controle.financas.pessoais.utils.FuncoesUtil;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarEmail;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNome;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNumeroCelular;
import br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarSenha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static br.com.sistema.controle.financas.pessoais.utils.validacoes.ValidarNumeroCelular.formatarNumeroCelular;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioDaoImpl.class);

    static FacadeService facadeService = FacadeService.getInstance();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        logger.info("Aplicação iniciada");

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

            UsuarioEntity usuario = facadeService.autenticarUsuario(email, senha);

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

            for (ContaEntity conta : contas) {
                System.out.println("Conta: " + conta.getNomeConta() +
                        " | Saldo R$ " + conta.getSaldoConta());
            }

            System.out.println("\n(1) Adicionar Conta");
            System.out.println("(2) Realizar Transação");
            System.out.println("(3) Minha Contas");
            System.out.println("(4) Extratos");
            System.out.println("(5) Deslogar");

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
                case 3:
                    minhasContas(input, idUsuario);
                    break;
                case 4:
                    mostrarExtrato(input, idUsuario);
                    break;
                case 5:
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

            Boolean emailExiste = facadeService.emailExiste(emailUsuario);
            if (Boolean.TRUE.equals(emailExiste)) {
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
        while (true){
            ContaEntity novaConta = new ContaEntity();

            do {
                String nomeConta = validarPrenchimentoEntrada(input,
                        "Digite o Nome do Banco",
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

            // Carregar e exibir tipos de contas a partir do banco de dados
            List<String> tiposConta = facadeService.obterTiposConta();
            if (tiposConta.isEmpty()) {
                System.err.println("Nenhum tipo de conta encontrado!");
                return;
            }

            System.out.println("Selecione o Tipo da Conta:");
            for (int i = 0; i < tiposConta.size(); i++) {
                System.out.println((i + 1) + ". " + tiposConta.get(i));
            }

            // Validação e escolha do tipo de conta
            int escolhaTipo;
            while (true) {
                String escolhaStr = validarPrenchimentoEntrada(input,
                        "Selecione o tipo de conta:",
                        "Numero não escolhido");
                if (!FuncoesUtil.ehNumero(escolhaStr)) {
                    System.out.println("Opção inválida! Digite um número válido.");
                } else {
                    escolhaTipo = Integer.parseInt(escolhaStr);
                    if (escolhaTipo < 1 || escolhaTipo > tiposConta.size()) {
                        System.err.println("Opção inválida.");
                    } else {
                        novaConta.setTipoConta(tiposConta.get(escolhaTipo - 1));
                        break;
                    }
                }
            }

            novaConta.setIdUsuario(idUsuario);
            novaConta.setIdSaldo(idSaldo);
            novaConta.setDataDeposito(Timestamp.valueOf(LocalDateTime.now()));

            facadeService.criarConta(novaConta);
            System.out.println(Constantes.cadastroConta);

            if (!desejaSair(input)) {
                break;
            }
        }
    }

    public static void registrarTransacao(Scanner input, Integer idUsuario) {
        while (true){
            List<ContaEntity> contas = facadeService.obterContasPorUsuario(idUsuario);

            if (contas.isEmpty()) {
                System.err.println(Constantes.contaNaoEncontrada);
                return;
            }

            exibirContas(contas);
            ContaEntity contaSelecionada = escolherConta(input, contas);

            if (contaSelecionada == null) {
                return;
            }

            System.out.println("Digite a descrição da transação:");
            String descricao = input.nextLine();

            CategoriaEnum categoriaSelecionada = escolhaCategoria(input);

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

            facadeService.realizarTransacao(contaSelecionada.getIdConta(), contaSelecionada.getIdSaldo(),
                                            descricao, categoriaSelecionada.name(), valor, tipo);
            System.out.println(Constantes.cadastroTransacao);

            if (!desejaSair(input)) {
                break;
            }
        }
    }

    public static CategoriaEnum escolhaCategoria(Scanner input){
        CategoriaEnum[] categoriaEnums = CategoriaEnum.values();

        System.out.println("Escolha uma categoria:");
        for (int i = 0; i < categoriaEnums.length; i++){
            System.out.printf("%d - %s%n", i + 1, categoriaEnums[i].name());
        }

        int escolha;
        while (true){
            System.out.println("Escolha á categoria:");
            String escolhaStr = input.nextLine().trim();
            if (FuncoesUtil.ehNumero(escolhaStr)){
                escolha = Integer.parseInt(escolhaStr);
                if (escolha > 0 && escolha <= categoriaEnums.length){
                    return categoriaEnums[escolha - 1];
                } else {
                    System.err.println("Escolha invalida. Tente novamente.");
                }
            } else {
                System.err.println("Digite apenas números.");
            }
        }
    }

    // Método para exibir as contas
    private static void exibirContas(List<ContaEntity> contas) {
        System.out.println("Selecione a conta para realizar a transação:");
        for (int i = 0; i < contas.size(); i++) {
            ContaEntity conta = contas.get(i);
            System.out.println((i + 1) + ". Conta: " + conta.getNomeConta() +
                    " | Tipo conta: " + conta.getTipoConta() +
                    " | Saldo R$ " + conta.getSaldoConta());
        }
    }

    // Método para capturar a escolha da conta
    private static ContaEntity escolherConta(Scanner input, List<ContaEntity> contas) {
        int escolha;
        while (true) {
            System.out.println("\nSelecione a conta:");
            String escolhaStr = input.nextLine().trim();

            if (!FuncoesUtil.ehNumero(escolhaStr)) {
                System.out.println("Opção invalida! Digite o numero correspondente a conta");
                continue;
            }

            escolha = Integer.parseInt(escolhaStr);
            if (escolha < 1 || escolha > contas.size()) {
                System.err.println("Opção invalida.");
            } else {
                break;
            }
        }
        return contas.get(escolha - 1);
    }

    private static void mostrarExtrato(Scanner input, Integer idUsuario) {
        while (true) {
            int[] mesAno = obterMesAno(input);
            int mes = mesAno[0];
            int ano = mesAno[1];

            List<ExtratoEntity> extratos = facadeService.obterExtratoPorMes(idUsuario, mes, ano);
            if (extratos.isEmpty()) {
                System.out.println("Nenhuma transação registrada nesta data informada!");
            }

            Map<String, Double> ganhosPorCategoria = new HashMap<>();
            Map<String, Double> gastosPorCategoria = new HashMap<>();

            System.out.println("\nExtrato de Transações:");
            TotaisGanhosGastosEntity totais = calcularGanhosGastos(extratos, ganhosPorCategoria, gastosPorCategoria);

            System.out.println("\nExtrato do mês:");
            exibirExtratoFinal(totais.getTotalGanho(), totais.getTotalGastos(), ganhosPorCategoria, gastosPorCategoria);

            if (!desejaSair(input)) {
                break;
            }
        }
    }

    private static int[] obterMesAno(Scanner input) {
        LocalDate hoje = LocalDate.now();
        int mes = hoje.getMonthValue();
        int ano = hoje.getYear();

        System.out.println("\nDeseja filtrar por uma data? (s/n)");
        String resposta = input.nextLine();
        while (!resposta.equals("s") && !resposta.equals("n")) {
            System.out.println("Opção invalida! Digite 's' para filtrar ou 'n' para mostrar o extrato completo");
            resposta = input.nextLine().trim().toLowerCase();
        }

        if (resposta.equalsIgnoreCase("s")) {
            System.out.println("Digite o mês (1-12):");
            mes = Integer.parseInt(input.nextLine());
            System.out.println("Digite o ano:");
            ano = Integer.parseInt(input.nextLine());
        }
        return new int[]{mes, ano};
    }

    private static TotaisGanhosGastosEntity calcularGanhosGastos(List<ExtratoEntity> extratos,
                                                                 Map<String, Double> ganhosPorCategoria,
                                                                 Map<String, Double> gastosPorCategoria){

        double totalGanhos = 0;
        double totalGastos = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (ExtratoEntity extrato : extratos) {
            String dataFormatada = extrato.getDataMovimentacao().toLocalDateTime().toLocalDate().format(formatter);
            String tipo = extrato.getTipoTransacao().equals("Ganho") ? "+ " : "- ";

            System.out.println("Data: " + dataFormatada + "\n" +
                    "| Conta: " + extrato.getNomeConta() +
                    "| Tipo: " + extrato.getTipoConta() +
                    "| Descrição: " + extrato.getDescricao() +
                    "| Categoria: " + extrato.getCategoria() +
                    "| Valor: " + tipo + extrato.getValor());

            if (extrato.getTipoTransacao().equals("Ganho")) {
                totalGanhos += extrato.getValor();
                ganhosPorCategoria.put(extrato.getCategoria(), ganhosPorCategoria.getOrDefault(extrato.getCategoria(), 0.0) + extrato.getValor());
            } else {
                totalGastos += extrato.getValor();
                gastosPorCategoria.put(extrato.getCategoria(), gastosPorCategoria.getOrDefault(extrato.getCategoria(), 0.0) + extrato.getValor());
            }
        }
        return new TotaisGanhosGastosEntity(totalGanhos, totalGastos);
    }

    private static void exibirExtratoFinal(double totalGanhos, double totalGastos, Map<String, Double> ganhosPorCategoria,
                                           Map<String, Double> gastosPorCategoria) {
        System.out.printf("Ganhos: R$ %.2f | Gastos: R$ %.2f\n", totalGanhos, totalGastos);

        if (totalGanhos > 0) {
            System.out.println("\nCategorias com mais Ganhos:");
            for (Map.Entry<String, Double> entry : ganhosPorCategoria.entrySet()) {
                double porcentagem = (entry.getValue() / totalGanhos) * 100;
                System.out.printf("%s: %.2f%%\n", entry.getKey(), porcentagem);
            }
        }

        if (totalGastos > 0) {
            System.out.println("\nCategorias com mais Gastos:");
            for (Map.Entry<String, Double> entry : gastosPorCategoria.entrySet()) {
                double porcentagem = (entry.getValue() / totalGastos) * 100;
                System.out.printf("%s: %.2f%%\n", entry.getKey(), porcentagem);
            }
        }
    }

    private static boolean desejaSair(Scanner input) {
        while (true) {
            System.out.println("\nDeseja sair para o menu inicial? (s/n):");
            String saida = input.nextLine().trim().toLowerCase();

            if (saida.equals("s")) {
                return false;
            } else if (saida.equals("n")) {
                return true;
            } else {
                System.out.println(Constantes.erroSN);
            }
        }
    }

    private static void minhasContas(Scanner input, Integer idUsuario){
        while (true){
            List<ContaEntity> contas = facadeService.obterContasPorUsuario(idUsuario);

            if (contas.isEmpty()){
                System.out.println("Nenhuma conta encontrada!");
                return;
            }

            exibirContas(contas);
            ContaEntity contaSelecionada = escolherConta(input, contas);

            if (contaSelecionada == null) {
                return;
            }

            System.out.println("Escolha uma opção: ");
            System.out.println("(1) Editar Conta");
            System.out.println("(2) Excluir Conta");
            System.out.println("(3) Voltar ao menu inicial");

            String escolhaOpcao = input.nextLine();
            if (!FuncoesUtil.ehNumero(escolhaOpcao)) {
                System.err.println(Constantes.OpcaoInvalida);
                continue;
            }
            int opcao = Integer.parseInt(escolhaOpcao);

            switch (opcao){
                case 1:
                    editarConta(input, contaSelecionada);
                    break;
                case 2:
                    System.out.println("Tem certeza que quer apagar essa conta?" +
                            "\nApós apagar, as transações dessa conta não aparecerão mais aqui!(s/n): ");

                    String confirmacao = input.nextLine().trim().toLowerCase();
                    if (confirmacao.equals("s")){
                        facadeService.excluirConta(contaSelecionada.getIdConta());
                        System.out.println("Conta excluida com sucesso.");
                    } else if (confirmacao.equals("n")){
                        System.out.println("Exclusão cancelada.");
                    } else {
                        System.err.println("Opção inválida! Operação cancelada.");
                    }
                    break;
                case 3:
                    return;
                default:
                    System.out.println(Constantes.OpcaoInvalida);
            }
        }
    }

    private static void editarConta(Scanner input, ContaEntity contaSelecionada) {
        System.out.println("Editar Conta:");

        // Nome da conta
        System.out.println("Nome atual: " + contaSelecionada.getNomeConta());
        System.out.println("Digite o novo nome da conta (ou aperte Enter para manter o nome atual):");
        String novoNome = input.nextLine();
        if (!ValidarNome.validarNome(novoNome)){
            System.err.println("Nome inválido! Nome não e permitido com números.");
        } else if (!novoNome.isBlank()) {
            contaSelecionada.setNomeConta(novoNome);
        }

        // Tipo da conta
        System.out.println("Tipo atual: " + contaSelecionada.getTipoConta());
        System.out.println("Digite o número do novo tipo de conta (ou aperte Enter para manter o tipo atual):");

        // Carregar e exibir tipos de contas a partir do banco de dados
        List<String> tiposConta = facadeService.obterTiposConta();
        if (tiposConta.isEmpty()) {
            System.err.println("Nenhum tipo de conta encontrado!");
            return;
        }

        // Exibir os tipos de conta disponíveis
        for (int i = 0; i < tiposConta.size(); i++) {
            System.out.println((i + 1) + ". " + tiposConta.get(i));
        }

        // Entrada para novo tipo de conta (ou manter o atual)
        String escolhaStr = input.nextLine();
        if (!escolhaStr.isBlank()) {
            if (FuncoesUtil.ehNumero(escolhaStr)) {
                int escolhaTipo = Integer.parseInt(escolhaStr);
                if (escolhaTipo >= 1 && escolhaTipo <= tiposConta.size()) {
                    contaSelecionada.setTipoConta(tiposConta.get(escolhaTipo - 1));
                } else {
                    System.err.println("Opção inválida. Mantendo o tipo de conta atual.");
                }
            } else {
                System.err.println("Opção inválida! Mantendo o tipo de conta atual.");
            }
        }

        // Chama o serviço para editar a conta
        facadeService.editarConta(contaSelecionada);
        System.out.println(Constantes.ContaEditada);
    }
}