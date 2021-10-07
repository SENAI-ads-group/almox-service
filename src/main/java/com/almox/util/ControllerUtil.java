package com.almox.util;

import com.almox.model.entidades.EntidadePadrao;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class ControllerUtil {

    public static URI getUriCriado(EntidadePadrao entidade) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidade.getId())
                .toUri();
    }
}
