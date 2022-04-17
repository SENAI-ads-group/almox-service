package org.almox.modules.departamento.service;

import org.almox.modules.auditoria.AuditoriaService;
import org.almox.modules.departamento.model.Departamento;
import org.almox.modules.departamento.dto.FiltroDepartamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface DepartamentoService extends AuditoriaService {
    Departamento criar(Departamento departamento);

    Departamento buscarPorId(UUID id);

    List<Departamento> buscar(FiltroDepartamento filtro, Sort sort);

    Page<Departamento> buscarPaginado(FiltroDepartamento filtro, Pageable pageable);

    Departamento atualizar(UUID id, Departamento departamento);

    void excluir(UUID id);
}
