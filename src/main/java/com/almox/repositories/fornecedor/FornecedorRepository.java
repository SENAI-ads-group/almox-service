package com.almox.repositories.fornecedor;

import com.almox.model.entidades.Fornecedor;
import com.almox.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor>, FornecedorRepositoryCustom {

}
