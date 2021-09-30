package com.almox.model.entidades;

import com.almox.model.enums.TipoEndereco;
import com.almox.model.enums.TipoTelefone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contato extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cont_id")
    private Long id;

    @NotBlank(message = "{contato.email.notblank}")
    @javax.validation.constraints.Email(message = "{contato.email.email}")
    @Column(name = "cont_eml_email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "{contato.telefone.telefone.notblank")
    @Column(name = "cont_tel1_telefone", nullable = false, unique = true)
    private String telefone1;

    @NotNull(message = "{contato.telefone.tipo.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "cont_tel1_tipo", nullable = false)
    private TipoTelefone tipoTelefone1;

    @NotBlank(message = "{contato.telefone.telefone.notblank")
    @Column(name = "cont_tel2_telefone", nullable = false, unique = true)
    private String telefone2;

    @NotNull(message = "{contato.telefone.tipo.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "cont_tel2_tipo", nullable = false)
    private TipoTelefone tipoTelefone2;


    @NotBlank(message = "{contato.endereco.logradouro.notblank")
    @Column(name = "cont_end_logradouro", nullable = false, unique = true)
    private String logradouro;

    @Column(name = "cont_end_complemento")
    private String complemento;

    @Column(name = "cont_end_numero")
    private String numero;

    @NotBlank(message = "{contato.endereco.cep.notblank")
    @Column(name = "cont_end_cep", nullable = false)
    private String cep;

    @NotBlank(message = "{contato.endereco.cidade.notblank")
    @Column(name = "cont_end_cidade", nullable = false)
    private String cidade;

    @NotBlank(message = "{contato.endereco.estado.notblank")
    @Column(name = "cont_end_estado", nullable = false)
    private String estado;

    @NotBlank(message = "{contato.endereco.bairro.notblank")
    @Column(name = "cont_end_bairro", nullable = false)
    private String bairro;

    @NotNull(message = "{contato.endereco.tipo.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "cont_end_tipo", nullable = false)
    private TipoEndereco tipoEndereco;
}