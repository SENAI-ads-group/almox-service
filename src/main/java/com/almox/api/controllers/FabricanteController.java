package com.almox.api.controllers;

import com.almox.model.dto.FiltroFabricanteDTO;
import com.almox.model.entidades.Fabricante;
import com.almox.services.ICrudService;
import com.almox.services.impl.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fabricantes")
public class FabricanteController extends CrudController<Fabricante, FiltroFabricanteDTO> {
    @Autowired
    private FabricanteService fabricanteService;

    @Override
    public ICrudService<Fabricante, FiltroFabricanteDTO> getService() {
        return fabricanteService;
    }
}
