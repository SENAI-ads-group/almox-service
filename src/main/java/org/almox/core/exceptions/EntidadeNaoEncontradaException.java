package org.almox.core.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException() {
        super("Entidade não encontrada.");
    }

    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

    public EntidadeNaoEncontradaException(Class<?> objClass, Object id) {
        super("Entidade " + objClass.getSimpleName() + " não encontrada. Id: " + id);
    }

    public EntidadeNaoEncontradaException(Class<?> objClass, String mensagemAdicional) {
        super("Entidade " + objClass.getSimpleName() + " não encontrada. " + mensagemAdicional);
    }
}
