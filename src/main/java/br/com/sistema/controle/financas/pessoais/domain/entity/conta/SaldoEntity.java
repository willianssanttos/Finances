package br.com.sistema.controle.financas.pessoais.domain.entity.conta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)

public class SaldoEntity implements Serializable {

    private Integer idSaldo;
    private Integer idUsuario;
    private Double saldoAtual;
    private Timestamp dataAtualizadaSaldo;

}
