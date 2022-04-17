package org.almox.modules.produto.model;

import lombok.*;
import org.almox.core.converters.ListaPalavraChaveConverter;
import org.almox.modules.auditoria.Auditavel;
import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.fornecedor.model.Fornecedor;
import org.almox.modules.grupo.model.Grupo;
import org.almox.modules.util.Constantes;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prod_produto")
@TableGenerator(name = "prod_codigo_sequence", initialValue = 10000)
public class Produto extends Auditavel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "prod_id")
    private UUID id;

    @NotBlank(message = "Produto.descricao.NotBlank")
    @Size(min = Constantes.MIN_SIZE_NOME, max = Constantes.MAX_SIZE_NOME, message = "{Produto.descricao.Size}")
    @Column(name = "prod_descricao", nullable = false, unique = true)
    private String descricao;

    @NotBlank(message = "{Produto.codigo.NotNull}")
    @Column(name = "prod_cod", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "prod_codigo_sequence")
    private Long codigo;

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
            inverseJoinColumns = @JoinColumn(name = "DPTO_ID")
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
