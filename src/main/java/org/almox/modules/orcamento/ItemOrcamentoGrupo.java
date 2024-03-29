package org.almox.modules.orcamento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.modules.common.EntidadePadrao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orc_dpto_orcamento_grupo_item")
public class ItemOrcamentoGrupo implements EntidadePadrao {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "orc_grp_item_id")
    private UUID id;

    @Column(name = "orc_grp_item_dt_lancamento", nullable = false)
    private LocalDateTime dataLancamento;

    @Column(name = "orc_grp_item_valor", nullable = false)
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "orc_grp_id", updatable = false, nullable = false)
    private OrcamentoGrupo orcamentoPertencente;
}
