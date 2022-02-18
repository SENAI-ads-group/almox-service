package com.almox.modules.movimento.repository;

import com.almox.modules.movimento.model.ItemMovimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMovimentoRepository extends JpaRepository<ItemMovimento, Long> {
}
