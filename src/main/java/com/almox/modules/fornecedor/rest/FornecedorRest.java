package com.almox.modules.fornecedor.rest;

import com.almox.modules.common.CrudRest;
import com.almox.modules.common.ICrudService;
import com.almox.modules.fornecedor.model.FiltroFornecedorDTO;
import com.almox.modules.fornecedor.model.Fornecedor;
import com.almox.modules.fornecedor.service.IFornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorRest extends CrudRest<Fornecedor, FiltroFornecedorDTO> {
    private final IFornecedorService fornecedorService;

    @Autowired
    public FornecedorRest(IFornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @Override

    public ICrudService<Fornecedor, FiltroFornecedorDTO> getService() {
        return fornecedorService;
    }
}
