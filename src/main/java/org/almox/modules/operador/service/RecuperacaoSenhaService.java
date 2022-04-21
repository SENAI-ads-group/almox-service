package org.almox.modules.operador.service;

import org.almox.modules.operador.dto.RecuperarEmailDTO;

public interface RecuperacaoSenhaService {

    RecuperarEmailDTO.Resposta recuperarEmail(RecuperarEmailDTO.Requisicao requisicaoRecuperarEmail);

}
