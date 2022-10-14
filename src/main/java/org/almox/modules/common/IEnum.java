package org.almox.modules.common;

import java.util.Arrays;

/**
 * Inteface a ser implementa por enum que possuem suas constantes exibidas na camada de visão
 *
 * @author patrick.ribeiro
 */
public interface IEnum {

    static <T extends IEnum> T fromType(T[] values, String type) {
        return Arrays.stream(values)//
                .filter(value -> value.getType().equals(type))//
                .findFirst()//
                .orElse(null);
    }

    String getDescricao();

    String getType();

}
