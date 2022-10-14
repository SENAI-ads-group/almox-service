package org.almox.modules.operador.dto;

import org.almox.modules.pessoa.dto.PessoaFisicaDTO;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

public class OperadorDTO {
    public UUID id;
    public PessoaFisicaDTO pessoa;
    public Set<String> funcoes;
}
