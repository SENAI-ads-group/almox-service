package com.almox.modules.fornecedor.repository;

import com.almox.modules.fornecedor.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository <Fornecedor,Long>, FornecedorRepositoryCustom{

}