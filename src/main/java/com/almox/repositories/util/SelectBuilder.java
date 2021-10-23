package com.almox.repositories.util;

import com.almox.model.enums.FiltroStatusAuditavel;
import com.almox.util.BooleanUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SelectBuilder {
    private final static String ALIAS = " e ";

    private StringBuilder sqlBuilder = new StringBuilder();
    private Map<String, Object> mapaParametros = new HashMap<>();

    public SelectBuilder(Class<?> classe) {
        sqlBuilder.append("SELECT") // SELECT e FROM Entidade e
                .append(ALIAS)
                .append("FROM ")
                .append(classe.getSimpleName())
                .append(ALIAS);
    }

    public SelectBuilder like(String campo, String valor) {
        return adicionarCondicao(" _ALIAS._CAMPO LIKE CONCAT('%', :_CAMPO,'%') ", campo, valor);
    }

    public SelectBuilder likeIgnoreCase(String campo, String valor) {
        return adicionarCondicao(" LOWER(_ALIAS._CAMPO) LIKE LOWER(CONCAT('%', :_CAMPO,'%')) ", campo, valor);
    }

    public SelectBuilder isEquals(String campo, Object valor) {
        return adicionarCondicao(" _ALIAS._CAMPO = :_CAMPO", campo, valor);
    }

    public SelectBuilder isNotNull(String campo) {
        return adicionarCondicao(" _ALIAS._CAMPO IS NOT NULL", campo);
    }

    public SelectBuilder isNull(String campo) {
        return adicionarCondicao(" _ALIAS._CAMPO IS NULL", campo);
    }

    public SelectBuilder in(String campo, Collection<?> colecao) {
        return adicionarCondicao(" _ALIAS._CAMPO IN :_CAMPO", campo, colecao);
    }

    public SelectBuilder filtrarStatusAuditavel(FiltroStatusAuditavel filtro) {
        if (filtro == null) {
            return this;
        }
        switch (filtro) {
            case APENAS_EXCLUIDOS:
                return isNotNull("excluidoPor");
            case APENAS_ATIVOS:
                return isNull("excluidoPor");
            default:
                return this;
        }
    }

    public <T> TypedQuery<T> criarQuery(EntityManager entityManager, Class<T> classe) {
        var query = entityManager.createQuery(this.toString(), classe);
        mapaParametros.forEach(query::setParameter);
        return query;
    }

    private SelectBuilder adicionarCondicao(String templateCondicao, String campo, Object valor) {
        if (BooleanUtil.algumNuloOuVazio(valor)) return this;

        templateCondicao = templateCondicao
                .replaceAll("_ALIAS", ALIAS)
                .replaceAll("_CAMPO", campo)
                .replaceAll("_VALOR", valor.toString());

        sqlBuilder.append(contemWhere() ? "AND" : "WHERE").append(templateCondicao);
        mapaParametros.put(campo, valor);
        return this;
    }

    private SelectBuilder adicionarCondicao(String templateCondicao, String campo) {
        templateCondicao = templateCondicao
                .replaceAll("_ALIAS", ALIAS)
                .replaceAll("_CAMPO", campo);

        sqlBuilder.append(contemWhere() ? "AND" : "WHERE").append(templateCondicao);
        return this;
    }

    private boolean contemWhere() {
        return sqlBuilder.toString().contains("WHERE");
    }

    @Override
    public String toString() {
        return sqlBuilder.toString();
    }
}
