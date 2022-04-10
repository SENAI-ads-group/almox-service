package org.almox.modules.operador.service;

import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.dto.OperadorFiltroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OperadorService {
    Operador criar(Operador operador);

    Operador buscarPorId(UUID id);

    Operador buscarPorLogin(String login);

    Optional<Operador> buscarPorLoginOptional(String login);

    List<Operador> buscar(OperadorFiltroDTO filtro, Sort sort);

    Page<Operador> buscarPaginado(OperadorFiltroDTO filtro, Pageable pageable);

    Operador atualizar(UUID id, Operador operador);

    void excluir(UUID id);
}
