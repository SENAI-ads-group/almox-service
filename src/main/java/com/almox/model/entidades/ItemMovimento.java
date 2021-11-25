package com.almox.model.entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "itm_item_movimento")
public class ItemMovimento extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itm_id")
    private Long id;

    @NotNull(message = "{itemMovimento.produto.notnull}")
    @ManyToOne
    @JoinColumn(name = "prod_id", nullable = false)
    private Produto produto;

    @DecimalMin(value = "0.0", inclusive = false, message = "{itemMovimento.quantidade.DecimalMin}")
    @Column(name = "itm_quantidade", nullable = false)
    private BigDecimal quantidade;

    @DecimalMin(value = "0.0", inclusive = false, message = "{ItemMovimento.custoLiquido.DecimalMin}")
    @Column(name = "itm_custo_liquido", nullable = false)
    private BigDecimal custoLiquido;

    @DecimalMin(value = "0.0", inclusive = false, message = "{ItemMovimento.custoBruto.DecimalMin}")
    @Column(name = "itm_custo_bruto", nullable = false)
    private BigDecimal custoBruto;

    @NotNull(message = "{itemMovimento.movimento.notnull}")
    @ManyToOne
    @JoinColumn(name = "mov_id", nullable = false)
    private Movimento movimento;

}
