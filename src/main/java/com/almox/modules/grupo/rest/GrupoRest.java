package com.almox.modules.grupo.rest;

import com.almox.modules.common.CrudRest;
import com.almox.modules.common.ICrudService;
import com.almox.modules.grupo.service.GrupoService;
import com.almox.modules.grupo.model.FiltroGrupoDTO;
import com.almox.modules.grupo.model.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoRest extends CrudRest<Grupo, FiltroGrupoDTO> {

    @Autowired
    private GrupoService grupoService;

    @Override
    public ICrudService<Grupo, FiltroGrupoDTO> getService() {
        return grupoService;
    }

}
