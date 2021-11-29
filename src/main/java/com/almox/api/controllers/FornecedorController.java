package com.almox.api.controllers;

import com.almox.model.dto.FiltroFornecedorDTO;
import com.almox.model.entidades.Fornecedor;
import com.almox.services.FornecedorService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorController extends CrudController<Fornecedor, FiltroFornecedorDTO> {
    @Getter
    private final FornecedorService service;

    @Autowired
    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

}
