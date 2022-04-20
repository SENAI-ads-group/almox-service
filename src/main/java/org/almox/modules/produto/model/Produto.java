package org.almox.modules.produto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
import javax.persistence.TableGenerator;
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
@Table(name = "PROD_PRODUTO")
@TableGenerator(name = "PROD_CODIGO_SEQUENCE", initialValue = 10000)
public class Produto extends Auditavel {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "PROD_ID")
    private UUID id;

    @NotBlank(message = "Produto.descricao.NotBlank")
    @Size(min = Constantes.MIN_SIZE_NOME, max = Constantes.MAX_SIZE_NOME, message = "{Produto.descricao.Size}")
    @Column(name = "PROD_DESCRICAO", nullable = false, unique = true)
    private String descricao;

    @NotBlank(message = "{Produto.codigo.NotNull}")
    @Column(name = "PROD_COD", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "PROD_CODIGO_SEQUENCE")
    private Long codigo;

    @NotBlank(message = "{Produto.codigoBarras.NotNull}")
    @Column(name = "PROD_COD_BARRAS", nullable = false, unique = true)
    private String codigoBarras;

    @Column(name = "PROD_CUSTO_MEDIO")
    private BigDecimal custoMedio;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PROD_PRODUTO_FORNECEDORES",
            joinColumns = @JoinColumn(name = "PROD_IDD"),
            inverseJoinColumns = @JoinColumn(name = "FORN_ID")
    )
    private Set<Fornecedor> fornecedores;

    @NotNull(message = "{Produto.unidadeMedida.NotBlank}")
    @Column(name = "PROD_UNIDADE_MEDIDA", nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    @Column(name = "PROD_DETALHES")
    private String detalhes;

    @Column(name = "PROD_PALAVRAS_CHAVES")
    @Convert(converter = ListaPalavraChaveConverter.class)
    private List<String> palavrasChave;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "PROD_PRODUTO_DEPARTAMENTOS",
            joinColumns = @JoinColumn(name = "prod_id"),
            inverseJoinColumns = @JoinColumn(name = "DPTO_ID")
    )
    private Set<Departamento> departamentos;

    @ManyToOne
    @JoinColumn(name = "GRP_ID")
    @NotNull(message = "{Produto.grupo.NotNull}")
    private Grupo grupo;

    @OneToOne
    @NotNull(message = "{Produto.configuracaoEstoque.NotNull}")
    @JoinColumn(name = "ESTQ_ID")
    private Estoque estoque;

}
