package org.almox.modules.produto.repository;

import org.almox.modules.produto.model.ConfiguracaoEstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracaoEstoqueProdutoRepository extends JpaRepository<ConfiguracaoEstoqueProduto, Long> {
}
