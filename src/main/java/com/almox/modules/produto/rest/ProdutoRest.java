package com.almox.modules.produto.rest;

import com.almox.modules.common.CrudRest;
import com.almox.modules.common.ICrudService;
import com.almox.modules.produto.model.FiltroProdutoDTO;
import com.almox.modules.produto.service.IProdutoService;
import com.almox.modules.produto.model.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoRest extends CrudRest<Produto, FiltroProdutoDTO> {
    private final IProdutoService produtoService;

    @Autowired
    public ProdutoRest(IProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public ICrudService<Produto, FiltroProdutoDTO> getService() {
        return produtoService;
    }
}
