package com.almox.model.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dpto_departamento")
public class Departamento extends Auditavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dpto_id")
    private Long id;

    @NotBlank(message = "{departamento.nome.notblank}")
    @Column(name = "dpto_nome", nullable = false)
    private String nome;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usr_dpto_usuarios_departamentos",
            joinColumns = @JoinColumn(name = "dpto_id"),
            inverseJoinColumns = @JoinColumn(name = "usr_id")
    )
    private Set<Usuario> usuarios;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "prod_dpto_produtos_departamentos",
            joinColumns = @JoinColumn(name = "dpto_id"),
            inverseJoinColumns = @JoinColumn(name = "prod_id")
    )
    private Set<Produto> produtos;

    @JsonIgnore
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<OrcamentoDepartamento> orcamentos;

}

