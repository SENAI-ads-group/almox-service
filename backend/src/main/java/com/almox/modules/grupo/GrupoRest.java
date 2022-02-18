package com.almox.modules.grupo;

import com.almox.modules.crud.CrudRest;
import com.almox.modules.grupo.model.FiltroGrupoDTO;
import com.almox.modules.grupo.model.Grupo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/grupos")
public class GrupoRest extends CrudRest<Grupo, FiltroGrupoDTO> {

    @Getter
    private final GrupoService service;

    @Autowired
    public GrupoRest(GrupoService service) {
        this.service = service;
    }
}
