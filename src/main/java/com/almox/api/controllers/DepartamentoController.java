package com.almox.api.controllers;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.entidades.Departamento;
import com.almox.services.ICrudService;
import com.almox.services.DepartamentoService;
import com.almox.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoController extends CrudController<Departamento, FiltroDepartamentoDTO> {

    private final DepartamentoService service;
    private final UsuarioService usuarioService;

    @Autowired
    public DepartamentoController(DepartamentoService service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;
    }

    @Override
    public ICrudService<Departamento, FiltroDepartamentoDTO> getService() {
        return service;
    }

    @GetMapping("/associados-usuario-logado")
    public ResponseEntity<List<Departamento>> buscarAssociadosUsuarioLogado(Principal principal) {
        var usuarioLogado = usuarioService.getUsuarioLogado();
        var lista = service.buscarAssociadosUsuario(usuarioLogado);
        return ResponseEntity.ok(service.buscarAssociadosUsuario(usuarioLogado));
    }
}