package com.almox.modules.fornecedor.repository;

import com.almox.modules.crud.CrudRepository;
import com.almox.modules.fornecedor.model.Fornecedor;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor>, FornecedorRepositoryCustom {

}
