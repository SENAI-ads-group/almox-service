package com.almox.modules.requisicao.repository;

import com.almox.modules.crud.CrudRepository;
import com.almox.modules.requisicao.model.Requisicao;
import org.springframework.stereotype.Repository;

@Repository
public interface RequisicaoRepository extends CrudRepository<Requisicao>, RequisicaoRepositoryCustom {
}
