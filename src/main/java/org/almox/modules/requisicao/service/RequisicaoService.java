package org.almox.modules.requisicao.service;

import org.almox.modules.requisicao.dto.FiltroRequisicao;
import org.almox.modules.requisicao.model.Requisicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface RequisicaoService {
    Requisicao criar(Requisicao requisicao);

    Requisicao buscarPorId(UUID id);

    List<Requisicao> buscar(FiltroRequisicao filtro, Sort sort);

    Page<Requisicao> buscarPaginado(FiltroRequisicao filtro, Pageable pageable);

    Requisicao atualizar(UUID id, Requisicao requisicao);

    void excluir(UUID id);
}
