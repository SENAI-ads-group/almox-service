package com.almox.api.controllers;

import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.entidades.Usuario;
import com.almox.services.ICrudService;
import com.almox.services.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController extends CrudController<Usuario, FiltroUsuarioDTO> {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public ICrudService<Usuario, FiltroUsuarioDTO> getService() {
        return usuarioService;
    }

    @GetMapping("/logado")
    public ResponseEntity<Usuario> usuarioLogado() {
        return ResponseEntity.ok(usuarioService.getUsuarioLogado());
    }
}
