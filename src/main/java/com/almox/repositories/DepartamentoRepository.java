package com.almox.repositories;

import com.almox.model.entidades.Departamento;
import com.almox.model.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>, DepartamentoRepositoryCustom {

    List<Departamento> findAllByUsuariosContaining(Usuario usuario);
}
