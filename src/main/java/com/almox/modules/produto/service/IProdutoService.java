package com.almox.modules.produto.service;

import com.almox.modules.common.ICrudService;
import com.almox.modules.produto.model.FiltroProdutoDTO;
import com.almox.modules.produto.model.Produto;

import java.math.BigDecimal;


public interface IProdutoService extends ICrudService<Produto, FiltroProdutoDTO> {
    BigDecimal calcularCustoMedio(Produto produto);
}
