package com.almox.services;

import com.almox.model.dto.FiltroProdutoDTO;
import com.almox.model.entidades.Produto;

import java.math.BigDecimal;


public interface IProdutoService extends ICrudService<Produto, FiltroProdutoDTO> {
    BigDecimal calcularCustoMedio(Produto produto);
}
