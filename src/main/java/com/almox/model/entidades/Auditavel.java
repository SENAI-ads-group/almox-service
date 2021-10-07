package com.almox.model.entidades;

import com.almox.converter.LocalDateTimeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
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
}
