package org.almox.core.config.operador;

import org.almox.modules.operador.dto.ContextoOperador;
import org.almox.modules.operador.repository.OperadorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
public class OperadorLogadoConfig {

    @Bean
    public ContextoOperador operadorLogado(OperadorRepository operadorRepository) {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(Object::toString)
                .flatMap(operadorRepository::buscarPorLogin);
    }
}
