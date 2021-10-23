package com.almox.services.impl;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.entidades.Departamento;
import com.almox.model.entidades.Usuario;
import com.almox.repositorios.DepartamentoRepository;
import com.almox.repositorios.OrcamentoDepartamentoRepository;
import com.almox.services.IDepartamentoService;
import com.almox.util.CondicaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepartamentoService implements IDepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final OrcamentoDepartamentoRepository orcamentoDepartamentoRepository;

    @Autowired
    public DepartamentoService(DepartamentoRepository departamentoRepository, OrcamentoDepartamentoRepository orcamentoDepartamentoRepository) {
        this.departamentoRepository = departamentoRepository;
        this.orcamentoDepartamentoRepository = orcamentoDepartamentoRepository;
    }

    public List<Departamento> buscarTodos(FiltroDepartamentoDTO filtro) {
        return departamentoRepository.findAll(filtro);
    }

    @Override
    public List<Departamento> buscarTodos() {
        return departamentoRepository.findAll();
    }

    public Departamento buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(departamentoRepository.findById(id));
    }

    public Departamento criar(@Valid Departamento entidade) {
        return salvar(entidade);
    }

    @Transactional
    public Departamento atualizar(Long id, Departamento entidade) {
        buscarPorId(id);
        return salvar(entidade);
    }

    private Departamento salvar(Departamento entidade) {
        return departamentoRepository.save(entidade);
    }

    @Override
    public void excluir(long id) {
        var departamentoEcontrado = buscarPorId(id);
        departamentoEcontrado.setDataExclusao(LocalDateTime.now());
        var usuarioExcluidor = new Usuario();
        usuarioExcluidor.setId(1L);
        departamentoEcontrado.setExcluidoPor(usuarioExcluidor);
        departamentoRepository.save(departamentoEcontrado);
    }

}
