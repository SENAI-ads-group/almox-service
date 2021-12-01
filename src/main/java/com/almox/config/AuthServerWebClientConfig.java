package com.almox.config;

import com.almox.api.controllers.AuthController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Configuration
public class AuthServerWebClientConfig {

    String authManagerUrl = "http://localhost:8082/auth-server/usuarios";


    @Autowired
    private AuthController authController;

    @Bean
    public WebClient webClientAuthServer(WebClient.Builder builder) {
        var token = authController.post(Map.of("usuario", "admin", "senha", "admin"))
                .get("access_token")
                .toString();

        return builder.baseUrl(authManagerUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Authorization", "Bearer "+token)
                .build();
    }
}
