package com.almox.api.controllers;

import com.almox.model.dto.FiltroGrupoDTO;
import com.almox.model.entidades.Grupo;
import com.almox.services.ICrudService;
import com.almox.services.impl.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoController extends CrudController<Grupo, FiltroGrupoDTO> {

    @Autowired
    private GrupoService grupoService;

    @Override
    public ICrudService<Grupo, FiltroGrupoDTO> getService() {
        return grupoService;
    }

}
