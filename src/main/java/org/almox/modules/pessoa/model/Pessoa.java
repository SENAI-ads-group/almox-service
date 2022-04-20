package org.almox.modules.pessoa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.common.EntidadePadrao;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "pess_pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PESS_TIPO", discriminatorType = DiscriminatorType.STRING)
public abstract class Pessoa implements EntidadePadrao {

    public static final String PESSOA_FISICA = "PF";
    public static final String PESSOA_JURIDICA = "PJ";

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "PESS_ID")
    private UUID id;

    @NotBlank(message = "{Pessoa.nome.NotBlank}")
    @Column(name = "PESS_NOME")
    private String nome;

    @NotBlank(message = "{Pessoa.email.NotBlank}")
    @Email(message = "{Pessoa.email.Email}")
    @Column(name = "PESS_EMAIL", unique = true)
    private String email;

    @NotBlank(message = "{Pessoa.telefone.NotBlank}")
    @Column(name = "PESS_TELEFONE")
    private String telefone;

    @NotBlank(message = "{Pessoa.endereco.logradouro.NotBlank}")
    @Column(name = "PESS_END_LOGRADOURO")
    private String logradouro;

    @Column(name = "PESS_END_COMPLEMENTO")
    private String complemento;

    @Column(name = "PESS_END_NUMERO")
    private String numero;

    @NotBlank(message = "{Pessoa.endereco.cep.NotBlank}")
    @Column(name = "PESS_END_CEP")
    private String cep;

    @NotBlank(message = "{Pessoa.endereco.cidade.NotBlank}")
    @Column(name = "PESS_END_CIDADE")
    private String cidade;

    @NotNull(message = "{Pessoa.endereco.uf.NotNull}")
    @Column(name = "PESS_END_UF")
    @Enumerated(EnumType.STRING)
    private UF uf;

    @NotBlank(message = "{Pessoa.endereco.bairro.NotBlank}")
    @Column(name = "PESS_END_BAIRRO")
    private String bairro;

    @Column(name = "PESS_TIPO", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipo;

    public abstract TipoPessoa getTipo();
}
