package com.almox.model.entidades;

import com.almox.converters.ListaPalavraChaveConverter;
import com.almox.model.enums.UnidadeMedida;
import com.almox.util.Constantes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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
    private Long id;

    @NotBlank(message = "Produto.descricao.NotBlank")
    @Size(min = Constantes.MIN_SIZE_NOME, max = Constantes.MAX_SIZE_NOME, message = "{Produto.descricao.Size}")
    @Column(name = "prod_descricao", nullable = false, unique = true)
    private String descricao;

    @DecimalMin(value = "0.0", inclusive = false, message = "{Produto.custoMedio.DecimalMin}")
    @NotNull(message = "{Produto.custoMedio.NotNull}")
    @Column(name = "prod_custo_medio", nullable = false)
    private BigDecimal custoMedio;

    @NotBlank(message = "{Produto.codigoBarras.NotBlank}")
    @Column(name = "prod_cod_barras", nullable = false, unique = true)
    private String codigoBarras;

    @NotNull(message = "{Produto.possuiLoteValidade.NotNull}")
    @Column(name = "prod_possui_lote_validade", nullable = false)
    private Boolean possuiLoteValidade;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Fornecedor> fornecedorres;

    @NotBlank(message = "{Produto.unidadeMedida.NotBlank}")
    @Column(name = "prod_unidade_medida", nullable = false)
    @Enumerated(EnumType.STRING)
    private UnidadeMedida unidadeMedida;

    @NotNull(message = "{Produto.fabricante.NotBlank}")
    @ManyToOne
    @JoinColumn(name = "fab_id")
    private Fabricante fabricante;

    @NotBlank(message = "{Produto.detalhes.NotBlank}")
    @Column(name = "prod_detalhes")
    private String detalhes;

    @Column(name = "prod_palavras_chaves")
    @Convert(converter = ListaPalavraChaveConverter.class)
    private List<String> palavrasChave;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Departamento> departamentos;

    @ManyToOne
    @JoinColumn(name = "grp_id")
    @NotNull(message = "{Produto.grupo.NotNull}")
    private Grupo grupo;

    @OneToOne
    @NotBlank(message = "{Produto.configuracaoEstoque.NotBlank}")
    @JoinColumn(name = "conf_estq_id")
    private ConfiguracaoEstoqueProduto configuracaoEstoque;

}
