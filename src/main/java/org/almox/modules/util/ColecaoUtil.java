package org.almox.modules.util;

import java.util.ArrayList;
import java.util.Collection;

public final class ColecaoUtil {

    public static <T> Collection<T> colecaoVaziaCasoSejaNula(Collection<T> collection) {
        if (collection == null) {
            return new ArrayList<>();
        }
        return collection;
    }
}
