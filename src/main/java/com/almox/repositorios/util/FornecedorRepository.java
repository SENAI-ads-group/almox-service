package com.almox.repositorios.util;

import com.almox.model.entidades.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository <Fornecedor,Long>{

}
