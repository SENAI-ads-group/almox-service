package org.almox.modules.pessoa.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum UF {
    AM("Amazonas", "Manaus"),
    AL("Alagoas", "Maceió"),
    AC("Acre", "Rio Branco"),
    AP("Amapá", "Macapá"),
    BA("Bahia", "Salvador"),
    PA("Pará", "Belém"),
    MT("Mato Grosso", "Cuiabá"),
    MG("Minas Gerais", "Belo Horizonte"),
    MS("Mato Grosso do Sul", "Campo Grande"),
    GO("Goiás", "Goiânia"),
    MA("Maranhão", "São Luís"),
    RS("Rio Grande do Sul", "Porto Alegre"),
    TO("Tocantins", "Palmas"),
    PI("Piauí", "Teresina"),
    SP("São Paulo", "São Paulo"),
    RO("Rondônia", "Porto Velho"),
    RR("Roraima", "Boa Vista"),
    PR("Paraná", "Curitiba"),
    CE("Ceará", "Fortaleza"),
    PE("Pernambuco", "Recife"),
    SC("Santa Catarina", "Florianópolis"),
    PB("Paraíba", "João Pessoa"),
    RN("Rio Grande do Norte", "Natal"),
    ES("Espírito Santo", "Vitória"),
    RJ("Rio de Janeiro", "Rio de Janeiro"),
    SE("Sergipe", "Aracaju"),
    DF("Distrito Federal", "Brasília");

    public String nome;
    public String capital;
}
