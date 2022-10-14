package org.almox.modules.requisicao.repository;

import org.almox.modules.requisicao.model.ItemRequisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemRequisicaoRepository extends JpaRepository<ItemRequisicao, UUID> {
}
