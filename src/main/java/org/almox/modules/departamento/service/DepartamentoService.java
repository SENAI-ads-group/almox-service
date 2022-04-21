package org.almox.modules.departamento.service;

import org.almox.modules.auditoria.AuditoriaService;
import org.almox.modules.departamento.dto.FiltroDepartamento;
import org.almox.modules.departamento.model.Departamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DepartamentoService extends AuditoriaService {
    Departamento criar(Departamento departamento);

    Departamento buscarPorId(UUID id);

    Page<Departamento> buscar(FiltroDepartamento filtro, Pageable paginacao);

    Page<Departamento> buscarExcluidos(FiltroDepartamento filtro, Pageable paginacao);

    Departamento atualizar(UUID id, Departamento departamento);

    void excluir(UUID id);
}
