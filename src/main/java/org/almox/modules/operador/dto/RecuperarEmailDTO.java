package org.almox.modules.operador.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;

public class RecuperarEmailDTO {

    @Schema(name = "RequisicaoRecuperacaoEmail")
    public static class Requisicao {
        @CPF
        @NotBlank
        public String cpfPessoaOperador;
    }

    @Schema(name = "RespostaRecuperacaoEmail")
    public static class Resposta {
        public String emailMascarado;
    }
}
