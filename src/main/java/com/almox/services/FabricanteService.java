package com.almox.services;

import com.almox.model.dto.FiltroFabricanteDTO;
import com.almox.model.entidades.Fabricante;
import com.almox.repositories.ContatoRepository;
import com.almox.repositories.fabricante.FabricanteRepository;
import com.almox.util.CondicaoUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class FabricanteService extends CrudService<Fabricante, FiltroFabricanteDTO> {
    @Getter
    private final FabricanteRepository repository;
    private final ContatoRepository contatoRepository;

    @Autowired
    public FabricanteService(FabricanteRepository repository, ContatoRepository contatoRepository) {
        this.repository = repository;
        this.contatoRepository = contatoRepository;
    }

    @Override
    protected List<Fabricante> _buscarTodos(FiltroFabricanteDTO filtro) {
        return repository.findAll(filtro);
    }

    @Override
    public List<Fabricante> _buscarTodos() {
        return repository.findAll();
    }

    @Override
    public Fabricante _buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(repository.findById(id));
    }

    public Fabricante _criar(@Valid Fabricante fabricante) {
        return salvar(fabricante);

    }

    @Override
    public Fabricante _atualizar(Long id, @Valid Fabricante entidade) {
        this.buscarPorId(id);
        return salvar(entidade);
    }

    private Fabricante salvar(Fabricante entidade) {
        entidade.setContato(contatoRepository.save(entidade.getContato()));
        return repository.save(entidade);
    }

}
