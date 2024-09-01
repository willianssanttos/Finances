package br.com.sistema.controle.financas.pessoais.utils.validacoes;

public class ValidarSenha {

    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#$%&])[A-Za-z\\d@#$%&]{8,}$";

    public static boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }
}
