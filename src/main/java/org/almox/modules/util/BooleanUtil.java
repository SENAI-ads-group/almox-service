package org.almox.modules.util;

import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

public final class BooleanUtil {

    private BooleanUtil() {
        super();
    }

    public static <EX extends RuntimeException> void throwIf(boolean condition, EX ex) {
        if (condition) {
            throw ex;
        }
    }

    public static <EX extends RuntimeException> void throwIf(boolean condition, EX ex, Consumer<EX> additionalActionsOnThrow) {
        if (condition) {
            if (additionalActionsOnThrow != null) {
                additionalActionsOnThrow.accept(ex);
            }
            throw ex;
        }
    }

    public static boolean algumNuloOuVazio(Object... objetos) {
        boolean algumNulo = false;
        for (var objeto : objetos) {
            algumNulo = isNuloOuVazio(objeto);

            if (algumNulo) {
                return true;
            }
        }
        return false;
    }

    public static boolean isNuloOuVazio(Object objeto) {
        if (objeto == null) {
            return true;
        }
        if (objeto instanceof String) {
            return ((String) objeto).strip().length() < 1;
        } else if (objeto instanceof Collection) {
            return ((Collection) objeto).isEmpty();
        } else if (objeto instanceof Map) {
            return ((Map) objeto).isEmpty();
        }
        return false;
    }

    public static boolean naoNuloOuVazio(Object objeto) {
        return !isNuloOuVazio(objeto);
    }

}
