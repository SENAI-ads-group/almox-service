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
@Table(name = "orc_dpto_orcamento_departamento_item")
public class ItemOrcamentoDepartamento extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orc_dpto_item_id")
    private Long id;

    @Column(name = "orc_dpto_item_dt_lancamento", nullable = false)
    private LocalDateTime dataLancamento;

    @Column(name = "orc_dpto_item_valor", nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "orc_dpto_id", updatable = false, nullable = false)
    private OrcamentoDepartamento orcamentoPertencente;
}
