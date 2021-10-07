package com.almox.util;

import com.almox.model.entidades.Auditavel;
import com.almox.model.enums.FiltroConsideracaoAtivos;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class ColecaoUtil {

    public static <E extends Auditavel> List<E> filtrarEntidadesPorExclusao(List<E> entidadeList, FiltroConsideracaoAtivos filtroConsideracaoAtivos) {
        if (filtroConsideracaoAtivos == null) {
            return entidadeList;
        }
        Predicate<Auditavel> predicateFiltro;
        switch (filtroConsideracaoAtivos) {
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
}
