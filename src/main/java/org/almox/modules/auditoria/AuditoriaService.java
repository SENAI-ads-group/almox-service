package org.almox.modules.auditoria;

import org.almox.modules.operador.model.Operador;

import java.time.LocalDateTime;

public interface AuditoriaService {

    default boolean isConsiderarTodos(FiltroStatusAuditoria filtro) {
        return FiltroStatusAuditoria.Tipo.CONSIDERAR_TODOS.equals(filtro.statusAuditoria);
    }

    default boolean isConsiderarApenasAtivos(FiltroStatusAuditoria filtro) {
        return FiltroStatusAuditoria.Tipo.APENAS_ATIVOS.equals(filtro.statusAuditoria);
    }

    default boolean isConsiderarApenasExcluidos(FiltroStatusAuditoria filtro) {
        return FiltroStatusAuditoria.Tipo.APENAS_EXCLUIDOS.equals(filtro.statusAuditoria);
    }

    default void atualizarEntidadeMantendoDatasAuditoria(Auditavel entidadeAtualizada, Auditavel entidadeAnterior) {
        entidadeAtualizada.setCriadoPor(entidadeAnterior.getCriadoPor());
        entidadeAtualizada.setDataCriacao(entidadeAnterior.getDataCriacao());
    }

    default void setExclusaoAuditoria(Auditavel auditavel, Operador operador) {
        auditavel.setDataExclusao(LocalDateTime.now());
        auditavel.setExcluidoPor(operador.clone());
    }
}
