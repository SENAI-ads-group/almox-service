package com.almox.repositories.departamento;

import com.almox.model.entidades.Departamento;
import com.almox.model.entidades.Usuario;
import com.almox.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends CrudRepository<Departamento>, DepartamentoRepositoryCustom {

    List<Departamento> findAllByUsuariosContaining(Usuario usuario);
}
