package com.almox.repositories.departamento;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.entidades.Departamento;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartamentoRepositoryCustom {

    List<Departamento> findAll(FiltroDepartamentoDTO filtro);
}
