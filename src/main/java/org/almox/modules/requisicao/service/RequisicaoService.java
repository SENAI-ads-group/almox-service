package org.almox.modules.requisicao.service;

import org.almox.modules.requisicao.dto.FiltroRequisicao;
import org.almox.modules.requisicao.model.Requisicao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RequisicaoService {
    Requisicao criar(Requisicao requisicao);

    Requisicao buscarPorId(UUID id);

    Page<Requisicao> buscar(FiltroRequisicao filtro, Pageable paginacao);

    void excluir(UUID id);

    void atenderRequisicao(UUID id);

    void cancelarRequisicao(UUID id);

    void entregarRequisicao(UUID id, Requisicao requisicaoEntregue);
}
