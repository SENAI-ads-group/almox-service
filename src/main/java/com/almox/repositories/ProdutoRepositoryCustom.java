package com.almox.repositories;

import com.almox.model.dto.FiltroProdutoDTO;
import com.almox.model.entidades.Produto;

import java.util.List;

public interface ProdutoRepositoryCustom {

    public List<Produto> findAll(FiltroProdutoDTO filtro);

}
