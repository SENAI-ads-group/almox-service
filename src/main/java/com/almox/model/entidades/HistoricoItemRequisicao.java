package com.almox.model.entidades;

import com.almox.model.enums.AcaoHistoricoItemRequisicao;
import com.almox.model.enums.StatusItemRequisicao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "hir_historico_item_requisicao")
public class HistoricoItemRequisicao extends EntidadePadrao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "req_id")
    private Long id;

    @Column(name = "hir_dt_requisicao", nullable = false)
    private LocalDateTime dataRequisicao;

    @Enumerated(EnumType.STRING)
    @Column(name = "hir_acao", nullable = false)
    private AcaoHistoricoItemRequisicao acao;

    @ManyToOne
    @JoinColumn(name = "id_usr_criacao", updatable = false, nullable = false)
    private Usuario criador;

    @ManyToOne
    @JoinColumn(name = "itr_id", updatable = false, nullable = false)
    private ItemRequisicao itemRequisicao;

    @Enumerated(EnumType.STRING)
    @Column(name = "hir_status_anterior", updatable = false, nullable = false)
    private StatusItemRequisicao statusAnterior;

    @Enumerated(EnumType.STRING)
    @Column(name = "hir_status_final", updatable = false, nullable = false)
    private StatusItemRequisicao statusFinal;

    @Column(name = "hir_quantidade_anterior", updatable = false, nullable = false)
    private BigDecimal quantidadeAnterior;

    @Column(name = "hir_quantidade_final", updatable = false, nullable = false)
    private BigDecimal quantidadeFinal;
}
