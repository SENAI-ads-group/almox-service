package com.almox.modules.produto.repository;

import com.almox.modules.produto.model.FiltroProdutoDTO;
import com.almox.modules.produto.model.Produto;

import java.util.List;

public interface ProdutoRepositoryCustom {

    public List<Produto> findAll(FiltroProdutoDTO filtro);
}
