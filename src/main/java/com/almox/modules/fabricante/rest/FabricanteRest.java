package com.almox.modules.fabricante.rest;

import com.almox.modules.common.CrudRest;
import com.almox.modules.common.ICrudService;
import com.almox.modules.fabricante.model.Fabricante;
import com.almox.modules.fabricante.model.FiltroFabricanteDTO;
import com.almox.modules.fabricante.service.FabricanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fabricantes")
public class FabricanteRest extends CrudRest<Fabricante, FiltroFabricanteDTO> {
    @Autowired
    private FabricanteService fabricanteService;

    @Override
    public ICrudService<Fabricante, FiltroFabricanteDTO> getService() {
        return fabricanteService;
    }
}
