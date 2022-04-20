package org.almox.modules.produto.service;

import org.almox.modules.auditoria.AuditoriaService;
import org.almox.modules.produto.model.FiltroProduto;
import org.almox.modules.produto.model.HistoricoEstoqueProduto;
import org.almox.modules.produto.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ProdutoService extends AuditoriaService {
    Produto criar(Produto operador);

    Produto buscarPorId(UUID id);

    Page<Produto> buscar(FiltroProduto filtro, Pageable paginacao);

    Page<Produto> buscarExcluidos(FiltroProduto filtro, Pageable paginacao);

    List<HistoricoEstoqueProduto> buscarHistoricoEstoque(UUID idProduto);

    Produto atualizar(UUID id, Produto operador);

    void excluir(UUID id);
}
