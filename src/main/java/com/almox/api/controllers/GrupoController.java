package com.almox.api.controllers;

import com.almox.model.dto.FiltroGrupoDTO;
import com.almox.model.entidades.Grupo;
import com.almox.services.GrupoService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoController extends CrudController<Grupo, FiltroGrupoDTO> {

    @Getter
    private final GrupoService service;

    @Autowired
    public GrupoController(GrupoService service) {
        this.service = service;
    }
}
