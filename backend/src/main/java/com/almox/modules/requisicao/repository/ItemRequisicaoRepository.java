package com.almox.modules.requisicao.repository;

import com.almox.modules.crud.CrudRepository;
import com.almox.modules.requisicao.model.ItemRequisicao;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRequisicaoRepository extends CrudRepository<ItemRequisicao> {
}
