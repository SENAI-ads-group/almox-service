package com.almox.modules.fornecedor.repository;

import com.almox.modules.fornecedor.model.FiltroFornecedorDTO;
import com.almox.modules.fornecedor.model.Fornecedor;

import java.util.List;


public interface FornecedorRepositoryCustom {

    List<Fornecedor> findAll(FiltroFornecedorDTO filtro);
}
