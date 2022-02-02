package com.almox.core.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AuthServerWebClientConfig {

    @Value("auth.manager.url")
    String authManagerUrl;

    @Bean
    public WebClient webClientAuthServer(WebClient.Builder builder) {
        return builder.baseUrl(authManagerUrl).build();
    }
}
