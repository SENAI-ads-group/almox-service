package com.almox.modules.orcamento.repository;

import com.almox.modules.orcamento.model.ItemOrcamentoDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrcamentoDepartamentoRepository extends JpaRepository<ItemOrcamentoDepartamento, Long> {
}
