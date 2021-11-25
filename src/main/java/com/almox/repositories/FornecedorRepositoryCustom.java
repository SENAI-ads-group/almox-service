package com.almox.repositories;

import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;

import java.util.List;


public interface FornecedorRepositoryCustom {

    List<Fornecedor> findAll(FiltroFornecedorDTO filtro);
}
