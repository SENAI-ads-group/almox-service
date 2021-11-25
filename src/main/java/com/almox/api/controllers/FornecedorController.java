package com.almox.api.controllers;
<<<<<<< HEAD
import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;
import com.almox.services.ICrudService;
import com.almox.services.impl.FornecedorService;
=======

import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;
import com.almox.services.ICrudService;
import com.almox.services.IFornecedorService;
>>>>>>> d06b990... Interligação de funcionalidades
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorController extends CrudController<Fornecedor, FiltroFornecedorDTO> {
<<<<<<< HEAD

    @Autowired
    private FornecedorService fornecedorService;
=======
    private final IFornecedorService fornecedorService;

    @Autowired
    public FornecedorController(IFornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }
>>>>>>> d06b990... Interligação de funcionalidades

    @Override
    public ICrudService<Fornecedor, FiltroFornecedorDTO> getService() {
        return fornecedorService;
    }
}
