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
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hep_historico_estoque_produto")
public class HistoricoEstoqueProduto extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hep_id")
    private Long id;

    @Column(name = "hep_dt_registro", nullable = false)
    private LocalDateTime dataRegistro;

    @Column(name = "hep_estoque_anterior", nullable = false)
    private BigDecimal estoqueAnterior;

    @Column(name = "hep_estoque_final", nullable = false)
    private BigDecimal estoqueFinal;

    @ManyToOne
    @JoinColumn(name = "itm_id", nullable = false)
    private ItemMovimento itemMovimento;

    @ManyToOne
    @JoinColumn(name = "prod_id", nullable = false)
    private Produto produto;

}
