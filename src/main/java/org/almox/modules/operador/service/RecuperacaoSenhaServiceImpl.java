package org.almox.modules.operador.service;

import lombok.RequiredArgsConstructor;
import org.almox.core.exceptions.EntidadeNaoEncontradaException;
import org.almox.modules.operador.dto.RecuperarEmailDTO;
import org.almox.modules.operador.model.Operador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecuperacaoSenhaServiceImpl implements RecuperacaoSenhaService {

    private final Validator validator;
    private final OperadorService operadorService;

    @Override
    public RecuperarEmailDTO.Resposta recuperarEmail(RecuperarEmailDTO.Requisicao requisicaoRecuperarEmail) {
        validator.validate(requisicaoRecuperarEmail);

        Operador operadorEncontrado = operadorService.buscarPorCpfPessoaOptional(requisicaoRecuperarEmail.cpfPessoaOperador)
                .orElseThrow(() -> new EntidadeNaoEncontradaException());

        String emailOperador = operadorEncontrado.getPessoa().getEmail();
        RecuperarEmailDTO.Resposta respostaRecuperacaoEmail = new RecuperarEmailDTO.Resposta();
        respostaRecuperacaoEmail.emailMascarado = mascararEmail(emailOperador);
        return respostaRecuperacaoEmail;
    }

    private String mascararEmail(String emailBruto) {
        String[] emailCortado = emailBruto.split("@");
        String emailMascarado = emailCortado[0].substring(0, 2);
        for (int i = 0; i < emailCortado[0].substring(3, emailCortado[0].length() - 3).length(); i++) {
            emailMascarado += "*";
        }
        emailMascarado += emailCortado[0].substring(emailCortado[0].length() - 3) + "@" + emailCortado[1];
        return emailMascarado;
    }
}
