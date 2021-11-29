package com.almox.services;

import com.almox.model.dto.FiltroProdutoDTO;
import com.almox.model.entidades.Produto;
import com.almox.repositories.produto.ProdutoRepository;
import com.almox.util.CondicaoUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService extends CrudService<Produto, FiltroProdutoDTO> {

    @Getter
    private final ProdutoRepository repository;
    private final ConfiguracaoEstoqueService configuracaoEstoqueService;

    @Autowired
    public ProdutoService(ProdutoRepository repository, ConfiguracaoEstoqueService configuracaoEstoqueService) {
        this.repository = repository;
        this.configuracaoEstoqueService = configuracaoEstoqueService;
    }

    @Override
    public Produto _buscarPorId(Long id) {
        return CondicaoUtil.verificarEntidade(repository.findById(id));
    }

    @Override
    public List<Produto> _buscarTodos() {
        return repository.findAll();
    }

    @Override
    public List<Produto> _buscarTodos(FiltroProdutoDTO filtro) {
        return repository.findAll(filtro);
    }

    @Override
    public Produto _criar(@Valid Produto entidade) {
        return salvar(entidade);
    }

    @Override
    public Produto _atualizar(Long id, Produto entidade) {
        this.buscarPorId(id);
        return salvar(entidade);
    }

    private Produto salvar(Produto entidade) {
        entidade.setConfiguracaoEstoque(configuracaoEstoqueService.salvar(entidade.getConfiguracaoEstoque()));
        return repository.save(entidade);
    }

    public BigDecimal calcularCustoMedio(Produto produto) {
        return produto.getCustoMedio(); // MOCK
    }
}
