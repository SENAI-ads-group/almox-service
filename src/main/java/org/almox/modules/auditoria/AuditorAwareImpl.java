package org.almox.modules.auditoria;

import lombok.RequiredArgsConstructor;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.repository.OperadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuditorAwareImpl implements AuditorAware<Operador> {

    private final OperadorRepository operadorRepository;

    @Override
    public Optional<Operador> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated())
            return Optional.empty();
        String login = authentication.getPrincipal().toString();
        return operadorRepository.findByLoginEquals(login);
    }
}
