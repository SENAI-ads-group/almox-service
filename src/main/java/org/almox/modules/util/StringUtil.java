package org.almox.modules.util;

import java.util.Optional;

public final class StringUtil {

    private StringUtil() {
        super();
    }

    public static String apenasNumeros(String str) {
        return Optional.ofNullable(str)
                .map(cpf -> cpf.replaceAll("[^0-9]", ""))
                .orElse(str);
    }
}
