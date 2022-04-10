package org.almox.modules.grupo.service;

import org.almox.modules.grupo.dto.FiltroGrupo;
import org.almox.modules.grupo.model.Grupo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface GrupoService {
    Grupo criar(Grupo grupo);

    Grupo buscarPorId(UUID id);

    List<Grupo> buscar(FiltroGrupo filtro, Sort sort);

    Page<Grupo> buscarPaginado(FiltroGrupo filtro, Pageable pageable);

    Grupo atualizar(UUID id, Grupo grupo);

    void excluir(UUID id);
}
