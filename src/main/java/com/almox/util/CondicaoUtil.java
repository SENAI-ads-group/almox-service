package com.almox.util;

import com.almox.exceptions.EntidadeNaoEncontradaException;
import com.almox.model.entidades.EntidadePadrao;
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