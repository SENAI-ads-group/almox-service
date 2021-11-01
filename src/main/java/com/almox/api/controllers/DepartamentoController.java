package com.almox.api.controllers;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.entidades.Departamento;
import com.almox.services.ICrudService;
import com.almox.services.impl.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoController extends CrudController<Departamento, FiltroDepartamentoDTO> {

    private final DepartamentoService departamentoService;

    @Autowired
    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @Override
    public ICrudService<Departamento, FiltroDepartamentoDTO> getService() {
        return departamentoService;
    }

}