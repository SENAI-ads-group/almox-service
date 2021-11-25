package com.almox.util;

import com.almox.model.entidades.Auditavel;
import com.almox.model.enums.FiltroStatusAuditavel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class ColecaoUtil {

    public static <E extends Auditavel> List<E> aplicarFiltroStatusAuditavel(List<E> entidadeList, FiltroStatusAuditavel filtroStatusAuditavel) {
        if (filtroStatusAuditavel == null) {
            return entidadeList;
        }
        Predicate<Auditavel> predicateFiltro;
        switch (filtroStatusAuditavel) {
            case APENAS_ATIVOS:
                predicateFiltro = auditavel -> !auditavel.isExcluido();
                break;
            case APENAS_EXCLUIDOS:
                predicateFiltro = Auditavel::isExcluido;
                break;
            default:
                predicateFiltro = auditavel -> true;
        }
        return entidadeList.stream()
                .filter(predicateFiltro)
                .collect(Collectors.toList());
    }

    public static <T> Collection<T> colecaoVaziaCasoSejaNula(Collection<T> collection) {
        if (collection == null) {
            return new ArrayList<>();
        }
        return collection;
    }
}
