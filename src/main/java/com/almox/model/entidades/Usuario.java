package com.almox.model.entidades;

import com.almox.converters.ListaPalavraChaveConverter;
import com.almox.converters.ListaRoleConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr_usuario")
public class Usuario extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_login", nullable = false, unique = true)
    private String login;

    @Column(name = "usr_roles")
    @Convert(converter = ListaRoleConverter.class)
    private List<String> roles;
}
