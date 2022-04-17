package org.almox.modules.operador.service;

import lombok.RequiredArgsConstructor;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperadorLogadoConfig {

    private final OperadorRepository operadorRepository;

    @ApplicationScope
    @Bean("operadorLogado")
    public Operador getOperadorLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            return null;
        String login = authentication.getPrincipal().toString();
        return operadorRepository.findByLoginEquals(login).orElse(null);
    }
}
