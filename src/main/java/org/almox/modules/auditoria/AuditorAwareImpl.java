package org.almox.modules.auditoria;

import lombok.RequiredArgsConstructor;
import org.almox.modules.operador.model.Operador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuditorAwareImpl implements AuditorAware<Operador> {

    private final Operador operadorLogado;

    @Override
    public Optional<Operador> getCurrentAuditor() {
        if (operadorLogado == null)
            return null;
        else
            return Optional.of(operadorLogado.clone());
    }
}
