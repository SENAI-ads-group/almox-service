package com.almox.model.entidades;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="dpto_departamento")
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

