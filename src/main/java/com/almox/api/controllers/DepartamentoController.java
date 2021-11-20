package com.almox.api.controllers;

import com.almox.model.dto.FiltroDepartamentoDTO;
import com.almox.model.entidades.Departamento;
import com.almox.services.ICrudService;
import com.almox.services.IDepartamentoService;
import com.almox.services.impl.UsuarioService;
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

    private final IDepartamentoService departamentoService;
    private final UsuarioService usuarioService;

    @Autowired
    public DepartamentoController(IDepartamentoService departamentoService, UsuarioService usuarioService) {
        this.departamentoService = departamentoService;
        this.usuarioService = usuarioService;
    }

    @Override
    public ICrudService<Departamento, FiltroDepartamentoDTO> getService() {
        return departamentoService;
    }

    @GetMapping("/associados-usuario-logado")
    public ResponseEntity<List<Departamento>> buscarAssociadosUsuarioLogado(Principal principal) {
        var usuarioLogado = usuarioService.getUsuarioLogado();
        var lista = departamentoService.buscarAssociadosUsuario(usuarioLogado);
        return ResponseEntity.ok(departamentoService.buscarAssociadosUsuario(usuarioLogado));
    }
}