package com.almox.modules.auditoria;

import com.almox.core.converters.LocalDateTimeConverter;
import com.almox.core.converters.UsuarioDTOConverter;
import com.almox.modules.usuario.model.UsuarioDTO;
import com.almox.modules.common.EntidadePadrao;
import com.almox.modules.util.DataUtil;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    protected LocalDateTime dataExclusao;

    @Column(name = "id_usr_criacao")
    @Convert(converter = UsuarioDTOConverter.class)
    @JsonBackReference("criadoPor")
    @CreatedBy
    private UsuarioDTO criadoPor;

    @Column(name = "id_usr_alteracao")
    @Convert(converter = UsuarioDTOConverter.class)
    @JsonBackReference("alteradoPor")
    @LastModifiedBy
    private UsuarioDTO alteradoPor;

    @Column(name = "id_usr_exclusao")
    @Convert(converter = UsuarioDTOConverter.class)
    @JsonBackReference("excluidoPor")
    private UsuarioDTO excluidoPor;

    public boolean isExcluido() {
        return excluidoPor != null;
    }

    @Transient
    public StatusAuditavel getStatusAuditoria() {
        if (excluidoPor != null)
            return StatusAuditavel.EXCLUIDO;
        if (DataUtil.ocorreuHoje(dataCriacao) && dataCriacao.isEqual(dataAlteracao))
            return StatusAuditavel.NOVO;
        else if (DataUtil.ocorreuHoje(dataAlteracao) && dataAlteracao.isAfter(dataCriacao))
            return StatusAuditavel.ATUALIZADO_RECENTMENTE;
        return StatusAuditavel.ATIVO;
    }
}
