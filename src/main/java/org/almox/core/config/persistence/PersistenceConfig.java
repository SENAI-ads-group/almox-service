package org.almox.core.config.persistence;

import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.repository.OperadorRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
public class PersistenceConfig {

    @Bean
    public AuditorAware<Operador> auditorAware(OperadorRepository operadorRepository) {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(Object::toString)
                .flatMap(operadorRepository::buscarPorLogin);
    }
}
