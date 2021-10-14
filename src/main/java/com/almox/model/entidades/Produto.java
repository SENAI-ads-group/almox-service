package com.almox.model.entidades;

import com.almox.model.enums.UnidadeMedida;
import com.almox.util.Constantes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "produto.customedio.notblank")
    @Column(name = "prod_custoMedio", nullable = false)
    private BigDecimal custoMedio;

    @NotBlank(message = "produto.codigoBarras.notblank")
    @Column(name = "prod_codBarras")
    private String codigoBarras;

    @NotBlank(message = "produto.possuiLoteValidade.notblank")
    @Column(name = "prod_loteValidade")
    private Boolean possuiLoteValidade;

    /*
    @OneToMany(fetch = FetchType.lAZY)
    private Set<Fornecedor> fornecedorres = new HashSet<>();*/

    @NotBlank(message = "produto.unidadeMedida.notblank")
    @Column(name = "prod_unidadeMedida")
    private UnidadeMedida unidadeMedida;

    @NotBlank(message = "produto.palavraChave.notblank")
    @Column(name = "prod_palavraChave")
    private List<String> palavrasChaves;

    @NotBlank(message = "produto.fabricante.notblank")
    @Column(name = "prod_fabricante")
    private Fabricante fabricante;

    @Column(name = "prod_detalhe")
    private String detalhe;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Departamento> departamentos = new HashSet<>();

    @OneToMany
    @NotBlank(message = "produto.grupo.notblank")
    @Column(name = "prod_grupo")
    private Grupo grupo;

    /*
    @NotBlank(message = "produto.configuracaoEstoque.notblank")
    @Column(name = "prod_configEstoque")
    private ConfiguracaoEstoque configuracaoEstoque;
     */
}
