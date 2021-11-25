package com.almox.api.controllers;

import com.almox.security.Token;
import com.almox.security.TokenProvider;
import com.almox.services.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenProvider tokenProvider;
    private final UsuarioService usuarioService;

    @Autowired
    public AuthController(TokenProvider tokenProvider, UsuarioService usuarioService) {
        this.tokenProvider = tokenProvider;
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Map<String, Object> post(@RequestBody Map<String, Object> request) {
        String usuario = (String) request.get("usuario");
        String senha = (String) request.get("senha");
        Token token = tokenProvider.getToken(usuario, senha);
        Map<String, Object> resposta = new HashMap<String, Object>();
        resposta.put("access_token", token.getAccessToken());
        usuarioService.getUsuarioLogado(); // TEMP
        return resposta;
    }

}
