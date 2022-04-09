package org.almox.modules.fornecedor.repository;

import org.almox.modules.fornecedor.model.Fornecedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {

    @Query("FROM Fornecedor as f WHERE f.pessoa.cnpj = :cnpj OR LOWER(f.pessoa.nome) LIKE LOWER(CONCAT('%',:nome,'%'))")
    List<Fornecedor> buscarPorCpjOuNome(@Param("cnpj") String cnpj, @Param("nome") String nome, Sort sort);

    @Query("FROM Fornecedor as f WHERE f.pessoa.cnpj = :cnpj OR LOWER(f.pessoa.nome) LIKE LOWER(CONCAT('%',:nome,'%'))")
    Page<Fornecedor> buscarPorCpjOuNome(@Param("cnpj") String cnpj, @Param("nome") String nome, Pageable pageable);
}
