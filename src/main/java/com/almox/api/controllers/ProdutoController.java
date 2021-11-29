package com.almox.api.controllers;

import com.almox.model.dto.FiltroProdutoDTO;
import com.almox.model.entidades.Produto;
import com.almox.services.ProdutoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController extends CrudController<Produto, FiltroProdutoDTO> {

    @Getter
    private final ProdutoService service;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.service = produtoService;
    }

}
