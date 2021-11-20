package com.almox.repositories.util;

import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;

import java.util.List;

public interface FornecedorRepositoryCustom {

    public List<Fornecedor> findAll(FiltroFornecedorDTO filtro);

}
