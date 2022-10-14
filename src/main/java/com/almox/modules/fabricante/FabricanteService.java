package com.almox.modules.fabricante;

import com.almox.modules.fabricante.model.Fabricante;
import com.almox.modules.fabricante.model.FiltroFabricanteDTO;
import com.almox.modules.fabricante.repository.FabricanteRepository;
import com.almox.modules.pessoa.ContatoRepository;
import com.almox.modules.crud.CrudService;
import com.almox.modules.util.CondicaoUtil;
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
