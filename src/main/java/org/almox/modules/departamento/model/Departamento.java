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
@Table(name = "DPTO_DEPARTAMENTO")
public class Departamento extends Auditavel {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "DPTO_ID")
    private UUID id;

    @NotBlank(message = "{departamento.nome.notblank}")
    @Column(name = "DPTO_DESCRICAO", nullable = false, unique = true)
    private String descricao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "DPTO_DEPARTAMENTO_OPERADORES",
            joinColumns = @JoinColumn(name = "DPTO_ID"),
            inverseJoinColumns = @JoinColumn(name = "OPE_ID")
    )
    private Set<Operador> operadores;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "DPTO_DEPARTAMENTO_PRODUTOS",
            joinColumns = @JoinColumn(name = "DPTO_ID", columnDefinition = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "PROD_ID", columnDefinition = "uuid")
    )
    private Set<Produto> produtos;

    @JsonIgnore
    @OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
    private List<OrcamentoDepartamento> orcamentos;

}

