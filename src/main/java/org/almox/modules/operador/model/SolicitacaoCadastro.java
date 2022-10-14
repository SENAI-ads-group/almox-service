package org.almox.modules.operador.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

public class SolicitacaoCadastro implements Serializable {
    @NotBlank
    public String nome;
    @CPF
    @NotBlank
    public String cpf;
    @NotBlank
    public String email;
    @NotBlank
    public String senha;
    public LocalDateTime dataCriacao;
}
