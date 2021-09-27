package com.almox.model.entidades;

import com.almox.model.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usr_usuario")
public class Usuario extends Auditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @NotBlank(message = "{usuario.nome.notblank}")
    @Column(name = "usr_nome", nullable = false)
    private String nome;

    @Email(message = "{usuario.email.email}")
    @NotBlank(message = "{usuario.email.notblank}")
    @Column(name = "usr_email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "{usuario.tipoUsuario.notnull")
    @Enumerated(EnumType.STRING)
    @Column(name = "usr_tipo", nullable = false)
    private TipoUsuario tipoUsuario;

    @NotBlank(message = "{usuario.senha.notblank}")
    @Column(name = "usr_senha", nullable = false)
    private String senha;

}
