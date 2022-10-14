package org.almox.modules.operador.dto;

import org.almox.modules.pessoa.dto.PessoaFisicaDTO;

import javax.validation.Valid;
import java.util.Set;

public class CriarOperadorDTO {
    public String login;
    public String senha;
    @Valid
    public PessoaFisicaDTO pessoa;
    public Set<String> funcoes;
}
