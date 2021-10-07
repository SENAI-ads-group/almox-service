package com.almox.model.entidades;

import com.almox.model.enums.StatusOrcamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class Orcamento extends Auditavel {

    @Future(message = "{orcamento.datainicio.futureorpresent}")
    @NotNull(message = "{orcamento.datainicio.notnull}")
    @Column(name = "orc_dt_inicio", nullable = false)
    private LocalDate dataInicio;

    @Future(message = "{orcamento.datatermino.futureorpresent}")
    @NotNull(message = "{orcamento.datatermino.notnull}")
    @Column(name = "orc_dt_termino", nullable = false)
    private LocalDate dataTermino;

    @DecimalMin(value = "0.0", inclusive = false, message = "{orcamento.valorlimite.decimalmin}")
    @NotNull(message = "{orcamento.valorlimite.notnull}")
    @Column(name = "orc_valor_limite", nullable = false)
    private BigDecimal valorLimite;

    @Enumerated(EnumType.STRING)
    @Column(name = "orc_status", nullable = false)
    private StatusOrcamento status;

}
