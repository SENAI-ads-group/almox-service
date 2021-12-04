package com.almox.repositories.departamento;

import com.almox.model.entidades.Departamento;
import com.almox.repositories.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento>, DepartamentoRepositoryCustom {

    @Query(value = "select * from dpto_departamento d join produtos_departamentos p on p.dpto_id = d.dpto_id where p.prod_id = ?1",
            nativeQuery = true)
    List<Departamento> findAllByProduct(Long idProduto);

    @Query(value = "select * from dpto_departamento d left join produtos_departamentos p on p.dpto_id = d.dpto_id where p.prod_id is null or p.prod_id != ?1",
            nativeQuery = true)
    List<Departamento> findAllByNotProduct(Long idProduto);
}
