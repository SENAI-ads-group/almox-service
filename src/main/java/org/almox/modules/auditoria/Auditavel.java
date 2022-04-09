package org.almox.modules.auditoria;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.almox.core.converters.LocalDateTimeConverter;
import org.almox.modules.common.EntidadePadrao;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.util.DataUtil;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditavel implements EntidadePadrao {

    private static final long serialVersionUID = -6830898710248097431L;

    @Column(name = "dt_criacao", nullable = false, updatable = false)
    @CreatedDate
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime dataCriacao;

    @Column(name = "dt_atualizacao")
    @Convert(converter = LocalDateTimeConverter.class)
    @LastModifiedDate
    protected LocalDateTime dataAlteracao;

    @Column(name = "dt_exclusao")
    @Convert(converter = LocalDateTimeConverter.class)
    protected LocalDateTime dataExclusao;

    @ManyToOne
    @JoinColumn(name = "ope_id_criacao", nullable = false)
    @JsonBackReference("criadoPor")
    @CreatedBy
    private Operador criadoPor;

    @ManyToOne
    @JoinColumn(name = "ope_id_alteracao")
    @JsonBackReference("alteradoPor")
    @LastModifiedBy
    private Operador alteradoPor;

    @ManyToOne
    @JoinColumn(name = "ope_id_exclusao")
    @JsonBackReference("excluidoPor")
    private Operador excluidoPor;

    @Column(name = "status_auditoria")
    private String statusAuditoria;

    public boolean isExcluido() {
        return excluidoPor != null;
    }

    public String getStatusAuditoria() {
        return isExcluido() ? "EXCLUIDO" : "ATIVO";
    }

    @Transient
    public StatusAuditavel getSituacao() {
        if (excluidoPor != null)
            return StatusAuditavel.EXCLUIDO;
        if (DataUtil.ocorreuHoje(dataCriacao) && dataCriacao.isEqual(dataAlteracao))
            return StatusAuditavel.NOVO;
        else if (DataUtil.ocorreuHoje(dataAlteracao) && dataAlteracao.isAfter(dataCriacao))
            return StatusAuditavel.ATUALIZADO_RECENTMENTE;
        return StatusAuditavel.ATIVO;
    }
}
