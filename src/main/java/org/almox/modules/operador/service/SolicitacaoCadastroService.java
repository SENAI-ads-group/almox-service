package org.almox.modules.operador.service;

import org.almox.modules.operador.dto.AprovarSolicitacaoCadastroDTO;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.model.SolicitacaoCadastro;

import java.util.Set;

public interface SolicitacaoCadastroService {
    void criar(SolicitacaoCadastro solicitacaoCadastro);

    Operador aprovar(String cpf, AprovarSolicitacaoCadastroDTO aprovarSolicitacaoCadastro);

    Set<SolicitacaoCadastro> buscar();

    void excluir(String cpf);
}
