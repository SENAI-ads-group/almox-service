package org.almox.modules.produto.service;

import org.almox.modules.produto.model.FiltroProduto;
import org.almox.modules.produto.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {
    Produto criar(Produto operador);

    Produto buscarPorId(UUID id);

    List<Produto> buscar(FiltroProduto filtro, Sort sort);

    Page<Produto> buscarPaginado(FiltroProduto filtro, Pageable pageable);

    Produto atualizar(UUID id, Produto operador);

    void excluir(UUID id);
}
