package br.com.sistema.controle.financas.pessoais.domain.entity.usuario;

import br.com.sistema.controle.financas.pessoais.domain.entity.conta.SaldoEntity;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity extends SaldoEntity implements Serializable {

    private Integer idUsuario;
    private String nomeUsuario;
    private String emailUsuario;
    private String senhaUsuario;
    private String numeroCelular;

}
