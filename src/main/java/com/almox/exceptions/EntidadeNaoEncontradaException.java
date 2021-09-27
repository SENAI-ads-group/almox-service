package com.almox.exceptions;

public class EntidadeNaoEncontradaException extends RuntimeException {

    public EntidadeNaoEncontradaException() {
        super("Entidade não encontrada.");
    }

    public EntidadeNaoEncontradaException(Class<?> objClass, Object id) {
        super("Entidade " + objClass.getSimpleName() + " não encontrada. Id: " + id);
    }

    public EntidadeNaoEncontradaException(Class<?> objClass, String mensagemAdicional) {
        super("Entidade " + objClass.getSimpleName() + " não encontrada. " + mensagemAdicional);
    }
}
