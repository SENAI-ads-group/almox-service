package org.almox.modules.pessoa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.common.EntidadePadrao;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
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
@DiscriminatorColumn(name = "pess_tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class Pessoa implements EntidadePadrao {

    public static final String PESSOA_FISICA = "PF";
    public static final String PESSOA_JURIDICA = "PJ";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pess_id")
    private UUID id;

    @NotBlank(message = "{Pessoa.nome.NotBlank}")
    @Column(name = "pess_nome")
    private String nome;

    @NotBlank(message = "{Pessoa.email.NotBlank}")
    @Email(message = "{Pessoa.email.Email}")
    @Column(name = "pess_email", unique = true)
    private String email;

    @NotBlank(message = "{Pessoa.telefone.NotBlank}")
    @Column(name = "pess_telefone")
    private String telefone;

    @NotBlank(message = "{Pessoa.endereco.logradouro.NotBlank}")
    @Column(name = "pess_end_logradouro")
    private String logradouro;

    @Column(name = "pess_end_complemento")
    private String complemento;

    @Column(name = "pess_end_numero")
    private String numero;

    @NotBlank(message = "{Pessoa.endereco.cep.NotBlank}")
    @Column(name = "pess_end_cep")
    private String cep;

    @NotBlank(message = "{Pessoa.endereco.cidade.NotBlank}")
    @Column(name = "pess_end_cidade")
    private String cidade;

    @NotNull(message = "{Pessoa.endereco.uf.NotNull}")
    @Column(name = "pess_end_uf")
    @Enumerated(EnumType.STRING)
    private UF uf;

    @NotBlank(message = "{Pessoa.endereco.bairro.NotBlank}")
    @Column(name = "pess_end_bairro")
    private String bairro;

    @Column(name = "pess_tipo", insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipo;

    @Override
    public boolean isNew() {
        return id == null;
    }

    public abstract TipoPessoa getTipo();
}
