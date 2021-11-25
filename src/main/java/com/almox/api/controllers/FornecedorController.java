package com.almox.api.controllers;

import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;
import com.almox.services.ICrudService;
import com.almox.services.IFornecedorService;
import com.almox.services.impl.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorController extends CrudController<Fornecedor, FiltroFornecedorDTO> {
    private final IFornecedorService fornecedorService;

    @Autowired
    public FornecedorController(IFornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @Override

    public ICrudService<Fornecedor, FiltroFornecedorDTO> getService() {
        return fornecedorService;
    }
}
