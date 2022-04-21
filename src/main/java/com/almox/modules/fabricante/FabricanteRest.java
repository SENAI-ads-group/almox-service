package com.almox.modules.fabricante;

import com.almox.modules.crud.CrudRest;
import com.almox.modules.crud.ICrudService;
import com.almox.modules.fabricante.model.Fabricante;
import com.almox.modules.fabricante.model.FiltroFabricanteDTO;
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
