package org.almox.modules.orcamento;

import org.almox.modules.orcamento.ItemOrcamentoDepartamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOrcamentoDepartamentoRepository extends JpaRepository<ItemOrcamentoDepartamento, Long> {
}
