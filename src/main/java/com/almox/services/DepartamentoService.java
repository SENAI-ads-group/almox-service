package com.almox.services;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.entidades.Departamento;
import com.almox.model.entidades.Usuario;
import com.almox.repositories.departamento.DepartamentoRepository;
import com.almox.repositories.departamento.OrcamentoDepartamentoRepository;
import com.almox.util.CondicaoUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
public class DepartamentoService extends CrudService<Departamento, FiltroDepartamentoDTO> {

    @Getter
    private final DepartamentoRepository repository;
    private final OrcamentoDepartamentoRepository orcamentoDepartamentoRepository;

    @Autowired
    public DepartamentoService(DepartamentoRepository repository, OrcamentoDepartamentoRepository orcamentoDepartamentoRepository) {
        this.repository = repository;
        this.orcamentoDepartamentoRepository = orcamentoDepartamentoRepository;
    }

    protected List<Departamento> _buscarTodos(FiltroDepartamentoDTO filtro) {
        return repository.findAll(filtro);
    }

    @Override
    protected List<Departamento> _buscarTodos() {
        return repository.findAll();
    }

    public Departamento _buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(repository.findById(id));
    }

    protected Departamento _criar(@Valid Departamento entidade) {
        return salvar(entidade);
    }

    @Transactional
    protected Departamento _atualizar(Long id, Departamento entidade) {
        this.buscarPorId(id);
        return salvar(entidade);
    }

    private Departamento salvar(Departamento entidade) {
        return repository.save(entidade);
    }

    public List<Departamento> buscarAssociadosUsuario(Usuario usuarioLogado) {
        return repository.findAllByUsuariosContaining(usuarioLogado);
    }
}
