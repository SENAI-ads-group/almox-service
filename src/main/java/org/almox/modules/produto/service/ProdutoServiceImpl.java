package org.almox.modules.produto.service;

import lombok.RequiredArgsConstructor;
import org.almox.modules.produto.model.FiltroProduto;
import org.almox.modules.produto.model.Produto;
import org.almox.modules.produto.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository fornecedorRepository;

    @Override
    public Produto criar(Produto operador) {
        return null;
    }

    @Override
    public Produto buscarPorId(UUID id) {
        return null;
    }

    @Override
    public List<Produto> buscar(FiltroProduto filtro, Sort sort) {
        return null;
    }

    @Override
    public Page<Produto> buscarPaginado(FiltroProduto filtro, Pageable pageable) {
        return null;
    }

    @Override
    public Produto atualizar(UUID id, Produto operador) {
        return null;
    }

    @Override
    public void excluir(UUID id) {

    }
}
