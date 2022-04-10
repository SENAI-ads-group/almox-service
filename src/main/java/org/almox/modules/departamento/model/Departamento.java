package org.almox.modules.departamento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.auditoria.Auditavel;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.orcamento.OrcamentoDepartamento;
import org.almox.modules.produto.model.Produto;

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
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dpto_departamento")
public class Departamento extends Auditavel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "dpto_id")
    private UUID id;

    @NotBlank(message = "{departamento.nome.notblank}")
    @Column(name = "dpto_descricao", nullable = false)
    private String descricao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "dpto_departamento_operadores",
            joinColumns = @JoinColumn(name = "dpto_id"),
            inverseJoinColumns = @JoinColumn(name = "ope_id")
    )
    private Set<Operador> operadores;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "produtos_departamentos",
            joinColumns = @JoinColumn(name = "dpto_id", columnDefinition = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "prod_id", columnDefinition = "uuid")
    )
    private Set<Produto> produtos;

    @JsonIgnore
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<OrcamentoDepartamento> orcamentos;

}

