package br.com.sistema.controle.financas.pessoais.utils;

public class Constantes {

    public static final String bemVindo = "BEM VINDO";
    public static final String OpcaoInvalida = "Opção invalida, utilize somente os números mostrados!";
    public static final String cadastroNome = "Nome invalido! O nome deve conter somente letras, com no minimo 2 e no máximo 100 caracteres!";
    public static final String cadastroEmail = "Insira um E-mail válido!";
    public static final String cadastroCelular = "Insira o número de celular ou número fixo com DDD(dois digitos, ex: 11912341234)";
    public static final String cadastroSenha = "Senha inválida. A senha deve ter no mínimo 8 caracteres, contendo letras, números e pelo menos um caractere especial (@, #, %, &, $).";
    public static final String confirmacaoSenha = "Senha incorreta! Verifique a senha digitada.";
    public static final String CadastroRealizadoUsuario = "Cadastrado realizado com sucesso! ";
    public static final String MensagemLoginUsuario = "Utilize o email e a senha definida no cadastro para logar na conta";

    //Mensagens de erro do servidor

    public static final String ErroServidor = "Erro ao tentar conectar ao servidor! Tente novamente mais tarde.";
    public static final String ErroRegistrarNoServidor = "Erro ao registrar no servidor!";
    public static final String ErroBuscarRegistroNoServidor = "Erro ao realizar buscar do registro no servidor!";
    public static final String ErroEditarRegistroNoServidor = "Erro ao atualizar registro no servidor!";
    public static final String ErroDeletarRegistroNoServidor = "Erro ao deletar registro no servidor!";

    //Mensagens info logger

    public static final String InfoRegistrar = "Dados '{}' salvos com sucesso, ";
    public static final String InfoBuscar = "Dados '{}' recuperados com sucesso, ";
    public static final String InfoEditar = "Dados '{}' atualizados no servidor com sucesso, ";
    public static final String InfoDeletar = "Dados '{}' deletado do servidor com sucesso, ";




    //Mensagem debug logger

    public static final String DebugRegistroProcesso = "Aplicação iniciada. ";
    public static final String DebugBuscarProcesso = "Iniciando o processo de busca no servidor.";
    public static final String DebugEditarProcesso = "Iniciando o processo de edição do registro no servidor.";
    public static final String DebugDeletarProcesso = "Iniciando o processo de deletar registro no servidor.";



    //mensagens de erros

    public static final String ErroCadastroUsuario = "Erro! Cadastrado não realizado!";
    public static final String EmailJaCadastrado = "Esse e-mail, já foi cadastrado!";
    public static final String ErroVerificarEmail = "O e-mail, não foi possivel ser verificado!";
    public static final String ErroCadastroConta = "Erro! Adicionar conta!";
    public static final String ContaEditada = "Conta editada com sucesso!";
    public static final String erroSN = "Opção invalida! Digite 's', para sair ou 'n' para continuar";
    public static final String ErroEditar = "Erro ao editar conta";
    public static final String ErroExcluir = "Erro ao excluir conta";

///Login ////

    public static final String loginConta = "Login realizado com sucesso!";
    public static final String erroLoginConta = "Usuário ou senha incorretos!";
    public static final String tipoTransacao = "Tipo transação invalido! Valores permitidos. Use 1 para receitas ou 2 para despesas!";
    public static final String cadastroTransacao = "Transação registrada com sucesso!";
    public static final String ErrocadastroTransacao = "Erro ao realizar registro transação!";
    public static final String ErrorRecuperarSaldo = "Erro ao recuperar o id do usuario, para obter o saldo!";
    public static final String ErrorRecuperarContas = "Erro ao recuperar contas do usuario!";
    public static final String ErrorRecuperarExtrato = "Erro ao recuperar extrato de transações!";

    ////// cadastro da conta //////

    public static final String contaNaoEncontrada = "Nenhuma conta encontrada para o usuário!";
    public static final String criarConta = "Cadastre uma conta, e começe acompanhar sua vida financeira!";
    public static final String cadastroNomeConta = "Insira o nome da conta";
    public static final String cadastroTipoConta = "Tipo contas invalido! Valores permitidos: CORRENTE\", \"POUPANÇA\", \"INVESTIMENTO, \"BENEFICIOS, \"OUTROS";
    public static final String cadastroSaldo = "O valor invalido!";
    public static final String cadastroConta = "Conta adicionada com sucesso!";


}
