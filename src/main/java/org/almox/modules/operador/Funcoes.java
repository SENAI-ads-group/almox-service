package org.almox.modules.operador;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Funcoes {
    public static final String ALMOXARIFE = "ALMOXARIFE";
    public static final String ADMINISTRADOR = "ADMINISTRADOR";
    public static final String REQUISITANTE = "REQUISITANTE";

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Secured(ADMINISTRADOR)
    public @interface ADMINISTRADOR {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Secured(ALMOXARIFE)
    public @interface ALMOXARIFE {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Secured({ALMOXARIFE, ADMINISTRADOR})
    public @interface ALMOXARIFE_ADMINISTRADOR {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Secured(REQUISITANTE)
    public @interface REQUISITANTE {
    }
}
