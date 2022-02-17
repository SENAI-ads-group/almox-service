package com.almox.modules.util;

public final class StringUtil {

    public static String prepararStringParaFiltro(String str) {
        return str != null ? str : Constantes.STRING_VAZIA;
    }
}
