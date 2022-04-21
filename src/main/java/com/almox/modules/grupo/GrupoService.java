package com.almox.modules.grupo;

import com.almox.core.exceptions.ViolacaoIntegridadeDadosException;
import com.almox.modules.crud.CrudService;
import com.almox.modules.grupo.model.FiltroGrupoDTO;
import com.almox.modules.grupo.model.Grupo;
import com.almox.modules.grupo.repository.GrupoRepository;
import com.almox.modules.util.CondicaoUtil;
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
