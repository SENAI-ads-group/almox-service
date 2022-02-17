package com.almox.core.security;

import com.almox.modules.usuario.AuthRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Configuration
public class AuthServerWebClientConfig {

    String authManagerUrl = "https://almox-fatesg-auth.herokuapp.com/usuarios";


    @Autowired
    private AuthRest authRest;

    @Bean
    public WebClient webClientAuthServer(WebClient.Builder builder) {
        var token = authRest.post(Map.of("usuario", "admin", "senha", "admin"))
                .get("access_token")
                .toString();

        return builder.baseUrl(authManagerUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization", "Bearer "+token)
                .build();
    }
}
