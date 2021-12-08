package com.almox.model.entidades;


import com.almox.model.enums.TipoDeMovimento;
import com.almox.model.enums.TipoOrigemMovimento;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Transient;
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

    @DecimalMin(value = "0.0", message = "{ItemMovimento.custoLiquido.DecimalMin}")
    @Column(name = "itm_custo_liquido")
    private BigDecimal custoLiquido;

    @DecimalMin(value = "0.0", message = "{ItemMovimento.custoBruto.DecimalMin}")
    @Column(name = "itm_custo_bruto")
    private BigDecimal custoBruto;

    @JsonBackReference
    @NotNull(message = "{itemMovimento.movimento.notnull}")
    @ManyToOne
    @JoinColumn(name = "mov_id", nullable = false)
    private Movimento movimento;

    @Transient
    private TipoDeMovimento tipoDeMovimento;
    @Transient
    private TipoOrigemMovimento tipoOrigemMovimento;
    @Transient
    private Long idOrigem;

}
