package org.almox.modules.movimento.repository;

import org.almox.modules.movimento.model.ItemMovimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMovimentoRepository extends JpaRepository<ItemMovimento, Long> {
}
