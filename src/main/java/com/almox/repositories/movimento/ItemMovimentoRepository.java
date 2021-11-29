package com.almox.repositories.movimento;

import com.almox.model.entidades.ItemMovimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMovimentoRepository extends JpaRepository<ItemMovimento, Long> {
}
