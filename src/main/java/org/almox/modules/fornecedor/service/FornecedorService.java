package org.almox.modules.fornecedor.service;

import org.almox.modules.auditoria.AuditoriaService;
import org.almox.modules.fornecedor.dto.FiltroFornecedor;
import org.almox.modules.fornecedor.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FornecedorService extends AuditoriaService {
    Fornecedor criar(Fornecedor fornecedor);

    Fornecedor buscarPorId(UUID id);

    Page<Fornecedor> buscar(FiltroFornecedor filtro, Pageable pageable);

    Page<Fornecedor>  buscarExcluidos(FiltroFornecedor filtro, Pageable paginacao);

    Fornecedor atualizar(UUID id, Fornecedor fornecedor);

    void excluir(UUID id);

}
