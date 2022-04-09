package org.almox.modules.produto.repository;

import org.almox.modules.produto.model.FiltroProduto;
import org.almox.modules.produto.model.Produto;

import java.util.List;

public interface ProdutoRepositoryCustom {
    public List<Produto> findAll(FiltroProduto filtro);
}
