package com.almox.model.entidades;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "itm_item_movimento")
public class ItemMovimento extends Auditavel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "itm_id")
    private Long id;

    @NotNull(message ="{itemMovimento.produto.notnull}")
    @ManyToOne
    @JoinColumn(name = "prod_id", nullable = false)
    private Produto produto;

    @DecimalMin(value = "0.0", inclusive = false, message = "{itemMovimento.quantidade.DecimalMin}")
    @NotNull(message = "{itemMovimento.quantidade.notnull}")
    @Column(name = "itm_quantidade", nullable = false)
    private BigDecimal quantidade;

    @DecimalMin(value = "0.0", inclusive = false, message = "{itemMovimento.valorItem.DecimalMin}")
    @NotNull(message = "{itemMovimento.valorItem.Dnotnull}")
    @Column(name = "itm_valorItem", nullable = false)
    private BigDecimal valorItem;

    @NotNull(message ="{itemMovimento.movimento.notnull}")
    @ManyToOne
    @JoinColumn(name = "mov_id", nullable = false)
    private Movimento movimento;

}
