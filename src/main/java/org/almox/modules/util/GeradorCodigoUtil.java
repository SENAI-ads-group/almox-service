package org.almox.modules.util;

import java.util.Random;

public class GeradorCodigoUtil {
    public static final String[] CARACTERES = {
            "A","B","C","D","E","F","G","H","I","J","K","L",
            "M","N","O","P","Q","R","S","T","U","V","W","X",
            "Y","Z", "0","1","2","3","4","5","6","7","8","9"
    };

    public static final int QUANTIDADE_CARACTERES = 8;


    public static String gerarCodigoAleatorio() {
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < QUANTIDADE_CARACTERES; i++) {
            int randomIndex = (int) ((Math.random() * (CARACTERES.length - 0)) + 0);
            stringBuffer.append(CARACTERES[randomIndex]);
        }
        return stringBuffer.toString();
    }
}
