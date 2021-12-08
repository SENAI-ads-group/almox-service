package com.almox.repositories.produto;

import com.almox.model.entidades.HistoricoEstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoEstoqueProdutoRepository extends JpaRepository<HistoricoEstoqueProduto, Long> {

    List<HistoricoEstoqueProduto> findAllByProdutoId(Long idProduto);
}
