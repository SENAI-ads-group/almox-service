package com.almox.modules.usuario;

import com.almox.modules.common.CrudRest;
import com.almox.modules.common.ICrudService;
import com.almox.modules.usuario.model.FiltroUsuarioDTO;
import com.almox.modules.usuario.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioRest extends CrudRest<Usuario, FiltroUsuarioDTO> {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioRest(UsuarioService usuarioService) {
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
