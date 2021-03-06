package com.almox.modules.produto.repository;

import com.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoEstoqueProdutoRepository extends JpaRepository<HistoricoEstoqueProduto, Long> {

    List<HistoricoEstoqueProduto> findAllByProdutoId(Long idProduto);
}
