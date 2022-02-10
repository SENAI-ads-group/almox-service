package com.almox.core.security;

import com.almox.core.exceptions.ApplicationRuntimeException;
import com.almox.modules.usuario.model.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class AuthManagerService {

    private final WebClient webClientAuthServer;

    @Autowired
    public AuthManagerService(WebClient webClientAuthServer) {
        this.webClientAuthServer = webClientAuthServer;
    }

    public UsuarioDTO buscarPorId(String id) {
        return webClientAuthServer.get()
                .uri("/" + id)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, this::tratarErroResponse4xx)
                .bodyToMono(UsuarioDTO.class)
                .block();
    }

    public UsuarioDTO buscarPorLogin(String login) {
        return webClientAuthServer.get()
                .uri("/login/" + login)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, this::tratarErroResponse4xx)
                .bodyToMono(UsuarioDTO.class)
                .block();
    }

    public List<UsuarioDTO> buscarTodos() {
        return webClientAuthServer.get()
                .uri("/listar/")
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, this::tratarErroResponse4xx)
                .bodyToMono(new ParameterizedTypeReference<List<UsuarioDTO>>() {
                }).block();
    }

    private Mono<ApplicationRuntimeException> tratarErroResponse4xx(ClientResponse response) {
            Mono<String> monoErro = response.bodyToMono(String.class);
            monoErro.subscribe(err -> {
                System.err.println("ERROR RESPONSE MESSAGE " + err);
            });
        throw new ApplicationRuntimeException(HttpStatus.FAILED_DEPENDENCY);
    }
}
