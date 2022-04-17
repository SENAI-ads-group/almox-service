package org.almox.modules.operador.repository;

import org.almox.modules.operador.model.Operador;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScope
@Repository
public interface OperadorRepository extends JpaRepository<Operador, UUID> {

    String QUERY_POR_NOME_EMAIL_PESSOA = "FROM Operador AS ope WHERE ope.pessoa.nome LIKE CONCAT('%', TRIM(LOWER(:nome)) ,'%') OR ope.pessoa.email = :email";

    Optional<Operador> findByLoginEquals(String login);

    @Query("FROM Operador as ope JOIN PessoaFisica as pf ON pf.id = ope.pessoa.id AND pf.cpf = :cpf")
    Optional<Operador> buscarPorCpfPessoa(@Param("cpf") String cpf);

    @Query(QUERY_POR_NOME_EMAIL_PESSOA)
    List<Operador> buscarPorNomeEmailPessoa(@Param("nome") String nome, @Param("email") String email, Sort sort);
    @Query(QUERY_POR_NOME_EMAIL_PESSOA)
    Page<Operador> buscarPorNomeEmailPessoa(@Param("nome") String nome, @Param("email") String email, Pageable sort);
}
