package com.almox.repositories;

import com.almox.model.entidades.ItemRequisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRequisicaoRepository extends JpaRepository<ItemRequisicao, Long> {
}
