package com.almox.modules.requisicao.repository;

import com.almox.modules.requisicao.model.ItemRequisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRequisicaoRepository extends JpaRepository<ItemRequisicao, Long> {
}
