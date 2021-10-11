package com.almox.repositorios.util;

import com.almox.util.BooleanUtil;
import com.almox.util.Constantes;

import javax.persistence.TypedQuery;

public final class QueryUtil {

    public static String adicionarLikeIgnoreCase(boolean primeiraCondicao, String campo, String filtro) {
        if (BooleanUtil.algumNuloOuVazio(filtro)) return Constantes.STRING_VAZIA;

        return String.format(
                " %s LOWER(%s) LIKE LOWER(CONCAT('%%', :%s,'%%')) ",
                primeiraCondicao ? "WHERE" : "AND",
                campo,
                campo.split("\\.")[1]
        );
    }

    public static String adicionarEq(boolean primeiraCondicao, String campo, Object filtro) {
        if (BooleanUtil.algumNuloOuVazio(filtro)) return Constantes.STRING_VAZIA;

        return String.format(
                " %s %s = :%s ",
                primeiraCondicao ? "WHERE" : "AND",
                campo,
                campo.split("\\.")[1]
        );
    }

    public static <T> void setParametro(TypedQuery<?> query, String sql, String nomeFiltro, T filtro) {
        if (sql.contains(":" + nomeFiltro)) {
            query.setParameter(nomeFiltro, filtro);
        }
    }

    public static String normalizarSql(String sql) {
        if (sql.contains("AND") && !sql.contains("WHERE")) { // NORMALIZA A POSSIBILIDADE DE TER UM AND SEM WHERE
            return sql.replaceFirst("AND", "WHERE");
        }
        return sql;
    }
}
