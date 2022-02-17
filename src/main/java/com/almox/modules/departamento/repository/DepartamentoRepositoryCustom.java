package com.almox.modules.departamento.repository;

import com.almox.modules.departamento.model.Departamento;
import com.almox.modules.departamento.model.FiltroDepartamentoDTO;

import java.util.List;

public interface DepartamentoRepositoryCustom {

    List<Departamento> findAll(FiltroDepartamentoDTO filtro);
}
