package com.almox.modules.requisicao.repository;

import com.almox.modules.requisicao.model.FiltroRequisicaoDTO;
import com.almox.modules.requisicao.model.Requisicao;

import java.util.List;

public interface RequisicaoRepositoryCustom {
    List<Requisicao> findAll(FiltroRequisicaoDTO filtro);
}
