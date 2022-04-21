package org.almox.modules.produto.repository;

import org.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface HistoricoEstoqueProdutoRepository extends JpaRepository<HistoricoEstoqueProduto, Long> {

    List<HistoricoEstoqueProduto> findAllByProdutoId(Long idProduto);

    @Query("FROM HistoricoEstoqueProduto AS his WHERE his.produto.id = :idProduto")
    List<HistoricoEstoqueProduto> buscarPorIdProduto(@Param("idProduto") UUID idProduto);
}
