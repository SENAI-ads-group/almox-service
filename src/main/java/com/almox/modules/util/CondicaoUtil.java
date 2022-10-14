package com.almox.modules.util;

import com.almox.core.exceptions.EntidadeNaoEncontradaException;
import com.almox.modules.common.EntidadePadrao;
import org.springframework.lang.NonNull;

import java.util.Optional;

public final class CondicaoUtil {
    private CondicaoUtil() {
        super();
    }

    public static <T extends EntidadePadrao> T verificarEntidade(final T entidade) {
        if (entidade == null || !entidade.estaPersistida()) {
            throw new EntidadeNaoEncontradaException();
        }
        return entidade;
    }

    public static <T extends EntidadePadrao> T verificarEntidade(@NonNull final Optional<T> optionalEntidade) {
        var entidade = optionalEntidade.orElseThrow(EntidadeNaoEncontradaException::new);
        return verificarEntidade(entidade);
    }
}