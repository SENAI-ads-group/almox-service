package com.almox.modules.departamento.rest;

import com.almox.modules.common.CrudRest;
import com.almox.modules.common.ICrudService;
import com.almox.modules.departamento.model.Departamento;
import com.almox.modules.departamento.model.FiltroDepartamentoDTO;
import com.almox.modules.departamento.service.IDepartamentoService;
import com.almox.modules.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoRest extends CrudRest<Departamento, FiltroDepartamentoDTO> {

    private final IDepartamentoService departamentoService;
    private final UsuarioService usuarioService;

    @Autowired
    public DepartamentoRest(IDepartamentoService departamentoService, UsuarioService usuarioService) {
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