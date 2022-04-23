package org.almox.modules.operador.repository;

import org.almox.modules.operador.model.Operador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Optional;
import java.util.UUID;

@ApplicationScope
@Repository
public interface OperadorRepository extends JpaRepository<Operador, UUID> {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Query("FROM Operador as ope WHERE ope.login = :login")
    Optional<Operador> buscarPorLogin(@Param("login") String login);

    @Query("FROM Operador as ope JOIN PessoaFisica as pf ON pf.id = ope.pessoa.id AND pf.cpf = :cpf")
    Optional<Operador> buscarPorCpfPessoa(@Param("cpf") String cpf);

    @Query("FROM Operador as ope JOIN PessoaFisica as pf ON pf.id = ope.pessoa.id AND ( pf.cpf = :cpf OR pf.email = :email )")
    Optional<Operador> buscarPorCpfOuEmailPessoa(@Param("cpf") String cpf, @Param("email") String email);

    @Query("FROM Operador AS ope                                                                          \n " +
            "WHERE                                                                                        \n " +
            "   LOWER(ope.pessoa.nome) LIKE CONCAT('%', TRIM(LOWER(:nome)), '%')                          \n " +
            "   AND LOWER(ope.pessoa.email) = LOWER(COALESCE(CAST( :email AS string) , ope.pessoa.email)) \n " +
            "   AND ope.pessoa.cpf = COALESCE(CAST( :cpf AS string) , ope.pessoa.cpf)                     \n "
    )
    Page<Operador> buscarPorNomeEmailPessoa(@Param("nome") String nome, @Param("email") String email, @Param("cpf") String cpf, Pageable sort);
}
