package br.com.sistema.controle.financas.pessoais.dao.conta;

import br.com.sistema.controle.financas.pessoais.model.conta.ContaEntity;
import org.springframework.jdbc.core.JdbcTemplate;

public class ContaDao {

    private final JdbcTemplate jdbcTemplate;

    public ContaDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public ContaEntity criarConta(ContaEntity conta){
        String sql = "SElECT inserir_conta(?,?,?,?,?)";
        jdbcTemplate.queryForObject(sql, new Object[]{
                        conta.getIdUsuario(), conta.getNomeConta(), conta.getSaldoConta(), conta.getTipoConta(), conta.getDataDeposito()},
                Integer.class);
        return conta;
    }
}

