package com.almox.model.entidades;

import com.almox.model.enums.TipoUsuario;
import com.almox.util.Constantes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr_usuario")
public class Usuario extends Auditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @NotBlank(message = "{usuario.nome.notblank}")
    @Size(min = Constantes.MIN_SIZE_NOME, max = Constantes.MAX_SIZE_NOME, message = "{usuario.nome.size}")
    @Column(name = "usr_nome", nullable = false)
    private String nome;

    @Email(message = "{usuario.email.email}")
    @NotBlank(message = "{usuario.email.notblank}")
    @Size(min = Constantes.MIN_SIZE_EMAIL, max = Constantes.MAX_SIZE_EMAIL, message = "{usuario.email.size}")
    @Column(name = "usr_email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "{usuario.tipoUsuario.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "usr_tipo", nullable = false)
    private TipoUsuario tipoUsuario;

    @Column(name = "usr_senha")
    private String senha;

}
