package com.almox.api.controllers;

import com.almox.model.enums.TipoUsuario;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/common", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonController {

    @GetMapping("/enumeradores")
    public Map<String, List<Enum<?>>> getEnumeradores() {
        Map<String, List<Enum<?>>> enums = Maps.newHashMap();
        enums.put("tiposUsuarios", Lists.newArrayList(TipoUsuario.values()));
        return enums;
    }
}
