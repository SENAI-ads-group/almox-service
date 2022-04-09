package org.almox.modules.departamento.service;

import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.departamento.model.FiltroDepartamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface DepartamentoService {
    Departamento criar(Departamento operador);

    Departamento buscarPorId(UUID id);

    List<Departamento> buscar(FiltroDepartamento filtro, Sort sort);

    Page<Departamento> buscarPaginado(FiltroDepartamento filtro, Pageable pageable);

    Departamento atualizar(UUID id, Departamento operador);

    void excluir(UUID id);
}
