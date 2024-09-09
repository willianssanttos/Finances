package br.com.sistema.controle.financas.pessoais.utils;

public class Constantes {

    public static final String bemVindo = "BEM VINDO";
    public static final String OpcaoInvalida = "Opção invalida, utilize somente os números mostrados no Menu!";
    public static final String cadastroNome = "Nome invalido! O nome deve conter somente letras, com no minimo 2 e no máximo 100 caracteres!";

    public static final String cadastroEmail = "Insira um Email válido";

    public static final String cadastroCelular = "Insira o número de celular ou número fixo com DDD(dois digitos, ex: 11912341234)";

    public static final String cadastroSenha = "Senha inválida. A senha deve ter no mínimo 8 caracteres, contendo letras, números e pelo menos um caractere especial (@, #, %, &, $).";

    public static final String CadastroRealizadoUsuario = "Cadastrado realizado com sucesso! ";

    public static final String MensagemLoginUsuario = "Utilize o email e a senha definida no cadastro para logar na conta";

    //mensagens de erros
    public static final String ErroCadastroUsuario = "Erro! Cadastrado não realizado!";

    public static final String EmailJaCadastrado = "Esse e-mail, já foi cadastrado!";

    public static final String ErroVerificarEmail = "O e-mail, não foi possivel ser verificado!";
    public static final String ErroCadastroConta = "Erro! Adicionar conta!";


///Login ////

    public static final String loginConta = "Login realizado com sucesso!";

    public static final String erroLoginConta = "Usuário ou senha incorretos!";


    public static final String statusCliente = "Preencha os campos abaixo para verificar o status da conta";

    public static final String tipoTransacao = "Tipo transação invalido! Valores permitidos. Use 1 para entrada e 2 para saida!";

    public static final String cadastroTransacao = "Transação registrada com sucesso!";

    public static final String ErrocadastroTransacao = "Erro ao realizar registro transação!";

    public static final String ErrorRecuperarIdUsuario = "Erro ao recuperar o id do usuario!";

    public static final String ErrorRecuperarContas = "Erro ao recuperar contas do usuario!";
    ///status da conta////
    public static final String clienteEmail = "Digite o E-MAIL";

    public static final String clienteSenha = "Digite a Senha";

    ////// cadastro da conta //////

    public static final String cadastroNomeConta = "Insira o nome da conta";

    public static final String cadastroTipoConta = "Tipo contas invalido! Valores permitidos: CONTA CORRENTE\", \"CONTA POUPANÇA\", \"CARTÃO DE CRÉDITO, \"INVESTIMENTO, \"BENEFÍCIOS, \"OUTROS";

    public static final String cadastroSaldo = "O valor invalido!";

    public static final String cadastroConta = "Conta adicionada com sucesso!";
    public static final String cadastroProdutoCodigo = "Insira o codigo do produto";

    public static final String cadastroProdutoDescricao = "Insira a descrição do produto";



    public static final String cadastroProdutoEstoque = "Insira o valor de estoque do produto";

    public static  void aberturaSistema(){
        System.out.println("MoneyMind");
    }

}
