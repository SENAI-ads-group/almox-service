package org.almox.modules.produto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.common.EntidadePadrao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prod_estoque")
public class Estoque implements EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conf_estq_id")
    private UUID id;

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
