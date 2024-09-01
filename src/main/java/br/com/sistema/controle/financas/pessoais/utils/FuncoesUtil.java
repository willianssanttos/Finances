package br.com.sistema.controle.financas.pessoais.utils;

import java.util.regex.Pattern;

public class FuncoesUtil {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean ehNumero(String entrada) {
        if (entrada == null){
            return false;
        }

        return NUMBER_PATTERN.matcher(entrada).matches();
    }
}
