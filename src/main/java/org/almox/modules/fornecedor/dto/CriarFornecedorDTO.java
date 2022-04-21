package org.almox.modules.fornecedor.dto;

import org.almox.modules.pessoa.dto.CriarPessoaJuridicaDTO;

import java.util.UUID;

public class CriarFornecedorDTO {
    public CriarPessoaFornecedor pessoa;

    public static class CriarPessoaFornecedor extends CriarPessoaJuridicaDTO {
        public UUID id;
    }
}
