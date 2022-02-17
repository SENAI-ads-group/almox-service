package com.almox.modules.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class DataUtil {

    public static boolean ocorreuHoje(LocalDateTime localDateTime) {
        var now = LocalDateTime.now();
        return now.getDayOfYear() == localDateTime.getDayOfYear() && now.getYear() == localDateTime.getYear();
    }

    public static boolean ocorreuHoje(LocalDate localDate) {
        var now = LocalDate.now();
        return now.getDayOfYear() == localDate.getDayOfYear() && now.getYear() == localDate.getYear();
    }

}
