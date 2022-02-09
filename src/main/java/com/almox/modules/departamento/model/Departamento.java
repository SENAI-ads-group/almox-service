package com.almox.modules.departamento.model;

import com.almox.core.converters.SetUsuarioDTOConverter;
import com.almox.modules.usuario.model.UsuarioDTO;
import com.almox.modules.auditoria.Auditavel;
import com.almox.modules.orcamento.OrcamentoDepartamento;
import com.almox.modules.produto.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
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

    @Column(name = "usuarios")
    @Convert(converter = SetUsuarioDTOConverter.class)
    private Set<UsuarioDTO> usuarios;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "produtos_departamentos",
            joinColumns = @JoinColumn(name = "dpto_id"),
            inverseJoinColumns = @JoinColumn(name = "prod_id")
    )
    private Set<Produto> produtos;

    @JsonIgnore
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<OrcamentoDepartamento> orcamentos;

}

