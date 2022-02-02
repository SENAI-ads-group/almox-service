package com.almox.modules.produto.model;

import com.almox.modules.common.ICrudService;

import java.math.BigDecimal;


public interface IProdutoService extends ICrudService<Produto, FiltroProdutoDTO> {
    BigDecimal calcularCustoMedio(Produto produto);
}
