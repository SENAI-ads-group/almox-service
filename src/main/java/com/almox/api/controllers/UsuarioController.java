package com.almox.api.controllers;

import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.dto.UsuarioDTO;
import com.almox.model.entidades.Usuario;
import com.almox.services.ICrudService;
import com.almox.services.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController extends CrudController<Usuario, FiltroUsuarioDTO, UsuarioDTO> {
    private final static UsuarioDTO USUARIO_DTO = new UsuarioDTO();

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public ICrudService<Usuario, FiltroUsuarioDTO> getService() {
        return usuarioService;
    }

    @Override
    public UsuarioDTO getDTO() {
        return USUARIO_DTO;
    }

}