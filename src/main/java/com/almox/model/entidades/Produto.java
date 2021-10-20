package com.almox.model.entidades;

import com.almox.model.enums.UnidadeMedida;
import com.almox.util.Constantes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
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

    @NotBlank(message = "produto.descricao.notblank")
    @Size(min = Constantes.MIN_SIZE_NOME, max = Constantes.MAX_SIZE_NOME, message = "{usuario.nome.size}")
    @Column(name = "prod_descricao", nullable = false, unique = true)
    private String descricao;

    @DecimalMin(value = "0.0", inclusive = false, message = "{produto.customedio.DecimalMin}")
    @NotNull(message = "produto.customedio.notnull")
    @Column(name = "prod_custoMedio", nullable = false)
    private BigDecimal custoMedio;

    @NotBlank(message = "produto.codigoBarras.notblank")
    @Column(name = "prod_codBarras",nullable = false, unique = true)
    private String codigoBarras;

    @NotNull(message = "produto.possuiLoteValidade.notnull")
    @Column(name = "prod_loteValidade",nullable = false)
    private Boolean possuiLoteValidade;

    /*
    @OneToMany(fetch = FetchType.lAZY)
    private Set<Fornecedor> fornecedorres = new HashSet<>();*/


    @NotBlank(message = "produto.unidadeMedida.notblank")
    @Column(name = "prod_unidadeMedida", nullable = false)
    @Enumerated
    private UnidadeMedida unidadeMedida;

    @NotNull(message = "produto.fabricante.notblank")
    @ManyToOne
    @JoinColumn(name = "fab_id")
    private Fabricante fabricante;

    @NotBlank(message = "produto.detalhe.notblank")
    @Column(name = "prod_detalhe")
    private String detalhe;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<PalavraChave> palavrasChave;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Departamento> departamentos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "grp_id")
    @NotNull(message = "produto.grupo.notnull")
    private Grupo grupo;

    @OneToOne
    @NotBlank(message = "produto.configuracaoEstoque.notblank")
    @JoinColumn (name = "confep_id")
    private ConfiguracaoEstoqueProduto configuracaoEstoque;

}
