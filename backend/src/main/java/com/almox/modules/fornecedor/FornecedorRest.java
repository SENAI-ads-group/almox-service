package com.almox.modules.fornecedor;

import com.almox.modules.crud.CrudRest;
import com.almox.modules.fornecedor.model.FiltroFornecedorDTO;
import com.almox.modules.fornecedor.model.Fornecedor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedorRest extends CrudRest<Fornecedor, FiltroFornecedorDTO> {
    @Getter
    private final FornecedorService service;

    @Autowired
    public FornecedorRest(FornecedorService service) {
        this.service = service;
    }

}
