package com.almox.repositories.produto;

import com.almox.model.entidades.HistoricoEstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoEstoqueProdutoRepository extends JpaRepository<HistoricoEstoqueProduto, Long> {
}
