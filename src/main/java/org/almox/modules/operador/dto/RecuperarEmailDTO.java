package org.almox.modules.operador.dto;

import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

public class RecuperarEmailDTO {

    public static class Requisicao {
        @CPF
        @NotBlank
        public String cpfPessoaOperador;
    }

    public static class Resposta {
        public String emailMascarado;
    }
}
