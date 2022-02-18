package com.almox.modules.util;

import com.almox.modules.common.EntidadePadrao;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class RestUtil {

    public static URI getUriCriado(EntidadePadrao entidade) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entidade.getId())
                .toUri();
    }
}
