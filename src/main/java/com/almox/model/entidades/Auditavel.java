package com.almox.model.entidades;

import com.almox.converter.LocalDateTimeConverter;
import com.almox.model.enums.StatusAuditavel;
import com.almox.util.DataUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Convert;
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
public abstract class Auditavel extends EntidadePadrao {

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
    @LastModifiedDate
    protected LocalDateTime dataExclusao;

    @ManyToOne
    @JoinColumn(name = "id_usr_criacao", updatable = false, nullable = false)
    @CreatedBy
    private Usuario criadoPor;

    @ManyToOne
    @JoinColumn(name = "id_usr_alteracao")
    @LastModifiedBy
    private Usuario alteradoPor;

    @ManyToOne
    @JoinColumn(name = "id_usr_exclusao")
    private Usuario excluidoPor;

    public boolean isExcluido() {
        return excluidoPor != null;
    }

    @Transient
    public StatusAuditavel getStatusAuditoria() {
        if (excluidoPor != null)
            return StatusAuditavel.EXCLUIDO;
        if (DataUtil.ocorreuHoje(dataCriacao) && dataCriacao.isEqual(dataAlteracao))
            return StatusAuditavel.NOVO;
        else if (DataUtil.ocorreuHoje(dataAlteracao))
            return StatusAuditavel.ATUALIZADO_RECENTMENTE;
        return StatusAuditavel.ATIVO;
    }
}
