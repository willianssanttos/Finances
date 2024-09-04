package br.com.sistema.controle.financas.pessoais.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceConfig {

    private static final String url = "jdbc:postgresql://localhost:5432/ControleFinanceiro";
    private static final String user = "postgres";
    private static final String password = "123456";

    public static Connection getConexao(){
        try {
            return
             DriverManager.getConnection(url, user, password);
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}