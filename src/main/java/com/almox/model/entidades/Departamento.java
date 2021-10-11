package com.almox.model.entidades;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
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

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Usuario> usuarios = new HashSet<>();

//    @ManyToMany(fetch = FetchType.LAZY)
//    private Set<Produto> produtos = new HashSet<>();

//    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
//    private List<OrcamentoDepartamento>orcamentos = new ArrayList<>();

}

