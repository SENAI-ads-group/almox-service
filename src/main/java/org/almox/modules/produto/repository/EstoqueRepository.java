package org.almox.modules.produto.repository;

import org.almox.modules.produto.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
