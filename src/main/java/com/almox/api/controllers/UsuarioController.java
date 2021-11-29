package com.almox.api.controllers;

import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.entidades.Usuario;
import com.almox.services.ICrudService;
import com.almox.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController extends CrudController<Usuario, FiltroUsuarioDTO> {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @Override
    public ICrudService<Usuario, FiltroUsuarioDTO> getService() {
        return service;
    }

    @GetMapping("/logado")
    public ResponseEntity<Usuario> usuarioLogado() {
        return ResponseEntity.ok(service.getUsuarioLogado());
    }
}
