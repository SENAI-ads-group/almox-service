package com.almox.api.controllers;

import com.almox.model.dto.FiltroProdutoDTO;
import com.almox.model.entidades.Produto;
import com.almox.services.ICrudService;
import com.almox.services.IProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController extends CrudController<Produto, FiltroProdutoDTO> {
    private final IProdutoService produtoService;

    @Autowired
    public ProdutoController(IProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Override
    public ICrudService<Produto, FiltroProdutoDTO> getService() {
        return produtoService;
    }
}
