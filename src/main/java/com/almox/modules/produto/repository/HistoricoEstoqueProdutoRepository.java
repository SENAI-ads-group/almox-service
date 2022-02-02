package com.almox.modules.produto.repository;

import com.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoEstoqueProdutoRepository extends JpaRepository<HistoricoEstoqueProduto, Long> {
}
