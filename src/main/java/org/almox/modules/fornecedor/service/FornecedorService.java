package org.almox.modules.fornecedor.service;

import org.almox.modules.fornecedor.model.FiltroFornecedor;
import org.almox.modules.fornecedor.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface FornecedorService {
    Fornecedor criar(Fornecedor operador);

    Fornecedor buscarPorId(UUID id);

    List<Fornecedor> buscar(FiltroFornecedor filtro, Sort sort);

    Page<Fornecedor> buscarPaginado(FiltroFornecedor filtro, Pageable pageable);

    Fornecedor atualizar(UUID id, Fornecedor operador);

    void excluir(UUID id);
}
