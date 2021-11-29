package com.almox.services;

import com.almox.exceptions.ViolacaoIntegridadeDadosException;
import com.almox.model.dto.FiltroGrupoDTO;
import com.almox.model.entidades.Grupo;
import com.almox.repositories.grupo.GrupoRepository;
import com.almox.util.CondicaoUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class GrupoService extends CrudService<Grupo, FiltroGrupoDTO> {

    @Getter
    private final GrupoRepository repository;

    @Autowired
    public GrupoService(GrupoRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Grupo _buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(repository.findById(id));
    }

    @Override
    protected List<Grupo> _buscarTodos(FiltroGrupoDTO filtro) {
        return repository.findAll(filtro);
    }

    @Override
    protected List<Grupo> _buscarTodos() {
        return repository.findAll();
    }

    @Override
    protected Grupo _criar(@Valid Grupo grupo) {
        if (!repository.findAllByDescricao(grupo.getDescricao()).isEmpty()) {
            throw new ViolacaoIntegridadeDadosException("Não foi possível cadastrar o Grupo. Descrição já existente.");
        }
        return salvar(grupo);
    }

    @Override
    protected Grupo _atualizar(Long id, Grupo grupo) {
        this.buscarPorId(id);
        return salvar(grupo);
    }

    private Grupo salvar(Grupo grupo) {
        return repository.save(grupo);
    }

}
