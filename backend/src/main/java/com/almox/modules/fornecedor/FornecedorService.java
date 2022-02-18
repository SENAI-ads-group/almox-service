package com.almox.modules.fornecedor;

import com.almox.modules.fornecedor.model.FiltroFornecedorDTO;
import com.almox.modules.fornecedor.model.Fornecedor;
import com.almox.modules.fornecedor.repository.FornecedorRepository;
import com.almox.modules.pessoa.ContatoRepository;
import com.almox.modules.crud.CrudService;
import com.almox.modules.util.CondicaoUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class FornecedorService extends CrudService<Fornecedor, FiltroFornecedorDTO> {

    @Getter
    private final FornecedorRepository repository;
    private final ContatoRepository contatoRepository;

    @Autowired
    public FornecedorService(FornecedorRepository repository, ContatoRepository contatoRepository) {
        this.repository = repository;
        this.contatoRepository = contatoRepository;
    }

    @Override
    protected List<Fornecedor> _buscarTodos(FiltroFornecedorDTO filtro) {
        return repository.findAll(filtro);
    }

    @Override
    protected List<Fornecedor> _buscarTodos() {
        return repository.findAll();
    }

    @Override
    protected Fornecedor _buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(repository.findById(id));
    }

    @Override
    protected Fornecedor _criar(@Valid Fornecedor fornecedor) {
        return salvar(fornecedor);
    }

    @Override
    protected Fornecedor _atualizar(Long id, @Valid Fornecedor entidade) {
        this.buscarPorId(id);
        return salvar(entidade);
    }

    private Fornecedor salvar(Fornecedor entidade) {
        entidade.setContato(contatoRepository.save(entidade.getContato()));
        return repository.save(entidade);
    }
}
