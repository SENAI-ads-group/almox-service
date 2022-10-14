package org.almox.modules.produto.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.common.EntidadePadrao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
@Table(name = "PROD_ESTOQUE")
public class Estoque implements EntidadePadrao {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "ESTQ_ID")
    private UUID id;

    @DecimalMin(value = "0.0", inclusive = false, message = "{ConfiguracaoEstoqueProduto.estoqueMinimo.DecimalMin}")
    @NotNull(message = "{ConfiguracaoEstoqueProduto.estoqueMinimo.NotNull}")
    @Column(name = "ESTQ_ESTOQUE_MIN", nullable = false)
    private BigDecimal estoqueMinimo;

    @Column(name = "EESTQ_ESTOQUE_ATUAL", nullable = false)
    private BigDecimal estoqueAtual;

    @DecimalMin(value = "0.0", inclusive = false, message = "{ConfiguracaoEstoqueProduto.estoqueMaximo.DecimalMin}")
    @NotNull(message = "{ConfiguracaoEstoqueProduto.estoqueMaximo.NotNull}")
    @Column(name = "ESTQ_ESTOQUE_MAX", nullable = false)
    private BigDecimal estoqueMaximo;

    @Column(name = "ESTQ_CONTROLA_ESTOQUE_MIN", nullable = false)
    private Boolean controlaEstoqueMinimo = Boolean.FALSE;

    @Column(name = "ESTQ_CONTROLA_ESTOQUE_MAX", nullable = false)
    private Boolean controlaEstoqueMaximo = Boolean.FALSE;

}
