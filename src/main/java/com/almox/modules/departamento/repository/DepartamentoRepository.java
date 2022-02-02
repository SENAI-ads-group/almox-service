package com.almox.modules.departamento.repository;

import com.almox.modules.departamento.model.Departamento;
import com.almox.modules.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long>, DepartamentoRepositoryCustom {

    List<Departamento> findAllByUsuariosContaining(Usuario usuario);
}
