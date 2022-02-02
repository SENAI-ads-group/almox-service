package com.almox.modules.orcamento.model;

import com.almox.modules.common.EntidadePadrao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orc_dpto_orcamento_grupo_item")
public class ItemOrcamentoGrupo extends EntidadePadrao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orc_grp_item_id")
    private Long id;

    @Column(name = "orc_grp_item_dt_lancamento", nullable = false)
    private LocalDateTime dataLancamento;

    @Column(name = "orc_grp_item_valor", nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "orc_grp_id", updatable = false, nullable = false)
    private OrcamentoGrupo orcamentoPertencente;
}
