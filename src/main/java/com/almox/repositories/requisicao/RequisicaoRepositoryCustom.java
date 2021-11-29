package com.almox.repositories.requisicao;

import com.almox.model.dto.FiltroRequisicaoDTO;
import com.almox.model.entidades.Requisicao;

import java.util.List;

public interface RequisicaoRepositoryCustom {
    List<Requisicao> findAll(FiltroRequisicaoDTO filtro);
}
