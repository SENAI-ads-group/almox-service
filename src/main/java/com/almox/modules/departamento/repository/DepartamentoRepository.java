package com.almox.modules.departamento.repository;

import com.almox.modules.crud.CrudRepository;
import com.almox.modules.departamento.model.Departamento;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento>, DepartamentoRepositoryCustom {

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
}
