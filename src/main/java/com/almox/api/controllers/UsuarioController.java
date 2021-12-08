package com.almox.api.controllers;

import com.almox.model.dto.FiltroUsuarioDTO;
import com.almox.model.dto.UsuarioDTO;
import com.almox.model.enums.TipoUsuario;
import com.almox.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @Autowired
    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping("/logado")
    public ResponseEntity<UsuarioDTO> usuarioLogado() {
        return ResponseEntity.ok(service.getUsuarioLogado());
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/listar-almoxarifes")
    public ResponseEntity<List<UsuarioDTO>> listarAlmoxarifes() {
        FiltroUsuarioDTO filtro = new FiltroUsuarioDTO();
        filtro.setTipoUsuario(TipoUsuario.ALMOXARIFE);
        return ResponseEntity.ok(service.buscarTodos(filtro));
    }
}
