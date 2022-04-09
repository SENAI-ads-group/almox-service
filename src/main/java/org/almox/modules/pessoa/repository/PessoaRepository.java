package org.almox.modules.pessoa.repository;

import org.almox.modules.pessoa.model.Pessoa;
import org.almox.modules.pessoa.model.TipoPessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query("FROM Pessoa as p WHERE p.tipo = ?1")
    List<Pessoa> findAllByTipoPessoa(String tipoPessoa);

    Optional<Pessoa> findByEmailEquals(String email);

    @Query("FROM Pessoa as p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%',:nome,'%')) OR p.email = :email")
    List<Pessoa> findAll(@Param("nome") String nome, @Param("email") String email, Sort sort);

    @Query("FROM Pessoa as p WHERE p.tipo = :tipo AND (LOWER(p.nome) LIKE LOWER(CONCAT('%',:nome,'%')) OR p.email = :email)")
    List<Pessoa> findAll(@Param("nome") String nome, @Param("email") String email, @Param("tipo") TipoPessoa tipo, Sort sort);

    @Query("FROM Pessoa as p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%',:nome,'%')) OR p.email = :email")
    Page<Pessoa> findAll(@Param("nome") String nome, @Param("email") String email, Pageable pageable);

    @Query("FROM Pessoa as p WHERE p.tipo = :tipo AND (LOWER(p.nome) LIKE LOWER(CONCAT('%',:nome,'%')) OR p.email = :email)")
    Page<Pessoa> findAll(@Param("nome") String nome, @Param("email") String email, @Param("tipo") String tipo, Pageable pageable);
}
