package org.almox.modules.departamento.repository;

import org.almox.modules.departamento.model.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, UUID> {

    @Query(value = "select * from dpto_departamento d join produtos_departamentos p on p.dpto_id = d.dpto_id where p.prod_id = ?1",
            nativeQuery = true)
    List<Departamento> findAllByProduct(Long idProduto);

    @Query(value = "select\n" +
            "    *\n" +
            "from\n" +
            "    dpto_departamento dpto\n" +
            "where\n" +
            "    dpto_id not in (\n" +
            "    select\n" +
            "        pd.dpto_id\n" +
            "    from\n" +
            "        produtos_departamentos pd\n" +
            "    where\n" +
            "        pd.dpto_id = dpto.dpto_id\n" +
            "        and pd.prod_id = ?1);",
            nativeQuery = true)
    List<Departamento> findAllByNotProduct(Long idProduto);

    @Query("FROM Departamento as d WHERE LOWER(d.descricao) LIKE CONCAT(:nome, '%')")
    List<Departamento> buscarPorDescricao(@Param("nome") String nome, Sort sort);

    @Query("FROM Departamento as d WHERE LOWER(d.descricao) LIKE CONCAT(:nome, '%')")
    Page<Departamento> buscarPorDescricao(@Param("nome") String nome, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT DISTINCT ope_id FROM dpto_departamento_operadores WHERE dpto_id = :idDepartamento")
    Set<UUID> buscarIdOperadoresAssociadosAoDepartamento(UUID idDepartamento);
}
