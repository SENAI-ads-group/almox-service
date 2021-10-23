package com.almox.repositorios;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.entidades.Departamento;

import java.util.List;

public interface DepartamentoRepositoryCustom {

    List<Departamento> findAll(FiltroDepartamentoDTO filtro);
}
