package org.almox.modules.grupo.service;

import org.almox.modules.auditoria.AuditoriaService;
import org.almox.modules.grupo.dto.FiltroGrupo;
import org.almox.modules.grupo.model.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface GrupoService extends AuditoriaService {
    Grupo criar(Grupo grupo);

    Grupo buscarPorId(UUID id);

    Page<Grupo> buscar(FiltroGrupo filtro, Pageable paginacao);

    Page<Grupo> buscarExcluidos(FiltroGrupo filtro, Pageable paginacao);

    Grupo atualizar(UUID id, Grupo grupo);

    void excluir(UUID id);
}
