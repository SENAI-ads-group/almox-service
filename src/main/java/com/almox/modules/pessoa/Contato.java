package com.almox.modules.pessoa;

import com.almox.modules.common.EntidadePadrao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cont_contato")
public class Contato extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cont_id")
    private Long id;

    @NotBlank(message = "{contato.email.notblank}")
    @javax.validation.constraints.Email(message = "{contato.email.email}")
    @Column(name = "cont_eml_email1", nullable = false)
    private String email1;

    @NotBlank(message = "{contato.email.notblank}")
    @javax.validation.constraints.Email(message = "{contato.email.email}")
    @Column(name = "cont_eml_emai2", nullable = false)
    private String email2;

    @NotBlank(message = "{contato.telefone.telefone.notblank}")
    @Column(name = "cont_tel1_telefone", nullable = false)
    private String telefone1;

    @NotNull(message = "{contato.telefone.tipo.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "cont_tel1_tipo", nullable = false)
    private TipoTelefone tipoTelefone1;

    @NotBlank(message = "{contato.telefone.telefone.notblank}")
    @Column(name = "cont_tel2_telefone", nullable = false)
    private String telefone2;

    @NotNull(message = "{contato.telefone.tipo.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "cont_tel2_tipo", nullable = false)
    private TipoTelefone tipoTelefone2;

    @NotBlank(message = "{contato.endereco.logradouro.notblank}")
    @Column(name = "cont_end_logradouro", nullable = false)
    private String logradouro;

    @Column(name = "cont_end_complemento")
    private String complemento;

    @Column(name = "cont_end_numero")
    private String numero;

    @NotBlank(message = "{contato.endereco.cep.notblank}")
    @Column(name = "cont_end_cep", nullable = false)
    private String cep;

    @NotBlank(message = "{contato.endereco.cidade.notblank}")
    @Column(name = "cont_end_cidade", nullable = false)
    private String cidade;

    @NotBlank(message = "{contato.endereco.estado.notblank}")
    @Column(name = "cont_end_estado", nullable = false)
    private String estado;

    @NotBlank(message = "{contato.endereco.bairro.notblank}")
    @Column(name = "cont_end_bairro", nullable = false)
    private String bairro;

    @NotNull(message = "{contato.endereco.tipo.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "cont_end_tipo", nullable = false)
    private TipoEndereco tipoEndereco;
}
