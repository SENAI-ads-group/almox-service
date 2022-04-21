package org.almox.modules.pessoa.dto;

import org.almox.modules.pessoa.model.TipoPessoa;
import org.almox.modules.pessoa.model.UF;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public abstract class PessoaDTO {
    public UUID id;

    @NotBlank(message = "{Pessoa.nome.NotBlank}")
    public String nome;

    @NotBlank(message = "{Pessoa.email.NotBlank}")
    @Email(message = "{Pessoa.email.Email}")
    public String email;

    @NotBlank(message = "{Pessoa.telefone.NotBlank}")
    public String telefone;

    @NotBlank(message = "{Pessoa.endereco.logradouro.NotBlank}")
    public String logradouro;

    @Column(name = "pess_end_complemento")
    public String complemento;

    public String numero;

    @NotBlank(message = "{Pessoa.endereco.cep.NotBlank}")
    public String cep;

    @NotBlank(message = "{Pessoa.endereco.cidade.NotBlank}")
    public String cidade;

    @NotNull(message = "{Pessoa.endereco.uf.NotNull}")
    public UF uf;

    @NotBlank(message = "{Pessoa.endereco.bairro.NotBlank}")
    public String bairro;

    public abstract TipoPessoa getTipo();
}
