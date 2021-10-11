package com.almox.exceptions;

public class ViolacaoIntegridadeDadosException extends RuntimeException {

    public ViolacaoIntegridadeDadosException(Throwable causa) {
        super(causa.getMessage());
    }

    public ViolacaoIntegridadeDadosException(String mensagem) {
        super(mensagem);
    }
}
