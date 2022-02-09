package com.almox.modules.produto.model;

import com.almox.modules.common.EntidadePadrao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "conf_estq_configuracao_estoque_produto")
public class ConfiguracaoEstoqueProduto extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conf_estq_id")
    private Long id;

    @DecimalMin(value = "0.0", inclusive = false, message = "{ConfiguracaoEstoqueProduto.estoqueMinimo.DecimalMin}")
    @NotNull(message = "{ConfiguracaoEstoqueProduto.estoqueMinimo.NotNull}")
    @Column(name = "conf_estq_estoque_min", nullable = false)
    private BigDecimal estoqueMinimo;

    @Column(name = "conf_estq_estoque_atual", nullable = false)
    private BigDecimal estoqueAtual;

    @DecimalMin(value = "0.0", inclusive = false, message = "{ConfiguracaoEstoqueProduto.estoqueMaximo.DecimalMin}")
    @NotNull(message = "{ConfiguracaoEstoqueProduto.estoqueMaximo.NotNull}")
    @Column(name = "conf_estq_estoque_max", nullable = false)
    private BigDecimal estoqueMaximo;

    @Column(name = "conf_estq_controla_estoque_min", nullable = false)
    private Boolean controlaEstoqueMinimo = Boolean.FALSE;

    @Column(name = "conf_estq_controla_estoque_max", nullable = false)
    private Boolean controlaEstoqueMaximo = Boolean.FALSE;

}
