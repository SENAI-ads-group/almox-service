package org.almox.modules.auditoria;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.UnauthorizedException;
import org.almox.modules.operador.model.Operador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Optional;

@Component
@RequestScope
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuditorAwareImpl implements AuditorAware<Operador> {

    private final Operador operadorLogado;

    @Override
    public Optional<Operador> getCurrentAuditor() {
        if (operadorLogado == null || "null".equals(operadorLogado.toString()))
            throw new UnauthorizedException();
        else
            return Optional.of(operadorLogado.clone());
    }
}
