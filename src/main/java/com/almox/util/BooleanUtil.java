package com.almox.util;

import java.util.Collection;

public final class BooleanUtil {

    private BooleanUtil() {
        super();
    }

    public static boolean ambosNulosOuAmbosIguais(Object obj1, Object obj2) {
        if (obj1 == null) {
            return obj2 == null;
        } else if (obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    public static boolean algumNuloOuVazio(Object... objetos) {
        boolean algumNuloOuVazio = false;
        for (Object objeto : objetos) {
            if (objeto == null) algumNuloOuVazio = true;
            else if (objeto instanceof String) algumNuloOuVazio = ((String) objeto).strip().length() < 1;
            else if (objeto instanceof Collection) algumNuloOuVazio = ((Collection<?>) objeto).isEmpty();

            if (algumNuloOuVazio) break;
        }
        return algumNuloOuVazio;
    }
}
