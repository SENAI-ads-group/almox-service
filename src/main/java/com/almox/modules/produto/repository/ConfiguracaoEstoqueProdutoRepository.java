package com.almox.modules.produto.repository;

import com.almox.modules.produto.model.ConfiguracaoEstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfiguracaoEstoqueProdutoRepository extends JpaRepository<ConfiguracaoEstoqueProduto, Long> {
}
