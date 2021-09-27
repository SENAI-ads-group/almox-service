package com.almox.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.time.DayOfWeek;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum DiaSemana implements IEnum {

    DOMINGO("Domingo", "D", 7, DayOfWeek.SUNDAY),
    SEGUNDA("Segunda", "S", 1, DayOfWeek.MONDAY),
    TERCA("Terça", "T", 2, DayOfWeek.TUESDAY),
    QUARTA("Quarta", "Q", 3, DayOfWeek.WEDNESDAY),
    QUINTA("Quinta", "Q", 4, DayOfWeek.THURSDAY),
    SEXTA("Sexta", "S", 5, DayOfWeek.FRIDAY),
    SABADO("Sábado", "S", 6, DayOfWeek.SATURDAY);

    @Getter
    private String descricao;
    @Getter
    private String abreviacao;
    @Getter
    private int numeroDiaSemana;
    @Getter
    private DayOfWeek dayOfWeek;

    DiaSemana(String descricao, String abreviacao, int numeroDiaSemana, DayOfWeek dayOfWeek) {
        this.descricao = descricao;
        this.abreviacao = abreviacao;
        this.numeroDiaSemana = numeroDiaSemana;
        this.dayOfWeek = dayOfWeek;
    }

    public static DiaSemana getByNumeroDiaSemana(int numeroDiaSemana) {
        for (DiaSemana diaSemana : DiaSemana.values()) {
            if (diaSemana.getNumeroDiaSemana() == numeroDiaSemana) {
                return diaSemana;
            }
        }
        return null;
    }

    public static DiaSemana getByDayOfWeek(DayOfWeek dayOfWeek) {
        for (DiaSemana diaSemana : values()) {
            if (dayOfWeek == diaSemana.dayOfWeek) {
                return diaSemana;
            }
        }
        return null;
    }

    @JsonCreator
    public static DiaSemana deserialize(@JsonProperty("type") String type) {
        return IEnum.fromType(values(), type);
    }

    public String getType() {
        return name();
    }
}
