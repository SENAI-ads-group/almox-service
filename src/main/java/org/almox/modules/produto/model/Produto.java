package org.almox.modules.produto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.core.converters.ListaPalavraChaveConverter;
import org.almox.modules.auditoria.Auditavel;
import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.fornecedor.model.Fornecedor;
import org.almox.modules.grupo.model.Grupo;
import org.almox.modules.util.Constantes;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prod_produto")
public class Produto extends Auditavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private UUID id;

    @NotBlank(message = "Produto.descricao.NotBlank")
    @Size(min = Constantes.MIN_SIZE_NOME, max = Constantes.MAX_SIZE_NOME, message = "{Produto.descricao.Size}")
    @Column(name = "prod_descricao", nullable = false, unique = true)
    private String descricao;

    @NotBlank(message = "{Produto.codigoBarras.NotNull}")
    @Column(name = "prod_cod_barras", nullable = false, unique = true)
    private String codigoBarras;

    @Column(name = "prod_custo_medio")
    private BigDecimal custoMedio;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "prod_forn_fornecedores_produtos",
            joinColumns = @JoinColumn(name = "prod_id"),
            inverseJoinColumns = @JoinColumn(name = "forn_id")
    )
    private Set<Fornecedor> fornecedores;

    @NotNull(message = "{Produto.unidadeMedida.NotBlank}")
    @Column(name = "prod_unidade_medida", nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    @Column(name = "prod_detalhes")
    private String detalhes;

    @Column(name = "prod_palavras_chaves")
    @Convert(converter = ListaPalavraChaveConverter.class)
    private List<String> palavrasChave;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "produtos_departamentos",
            joinColumns = @JoinColumn(name = "prod_id"),
            inverseJoinColumns = @JoinColumn(name = "dpto_id")
    )
    private Set<Departamento> departamentos;

    @ManyToOne
    @JoinColumn(name = "grp_id")
    @NotNull(message = "{Produto.grupo.NotNull}")
    private Grupo grupo;

    @OneToOne
    @NotNull(message = "{Produto.configuracaoEstoque.NotNull}")
    @JoinColumn(name = "conf_estq_id")
    private Estoque estoque;

}
