package org.almox.modules.requisicao.repository;

import org.almox.modules.requisicao.model.FiltroRequisicaoDTO;
import org.almox.modules.requisicao.model.Requisicao;

import java.util.List;

public interface RequisicaoRepositoryCustom {
    List<Requisicao> findAll(FiltroRequisicaoDTO filtro);
}
