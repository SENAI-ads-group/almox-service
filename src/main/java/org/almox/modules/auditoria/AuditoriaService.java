package org.almox.modules.auditoria;

import org.almox.modules.operador.model.Operador;

import java.time.LocalDateTime;

public interface AuditoriaService {

    default void atualizarEntidadeMantendoDatasAuditoria(Auditavel entidadeAtualizada, Auditavel entidadeAnterior) {
        entidadeAtualizada.setCriadoPor(entidadeAnterior.getCriadoPor());
        entidadeAtualizada.setDataCriacao(entidadeAnterior.getDataCriacao());
    }

    default void setExclusaoAuditoria(Auditavel auditavel, Operador operador) {
        auditavel.setDataExclusao(LocalDateTime.now());
        auditavel.setExcluidoPor(operador.clone());
    }
}
