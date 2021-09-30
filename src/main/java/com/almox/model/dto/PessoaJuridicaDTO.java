package com.almox.model.dto;

import com.almox.model.entidades.Contato;
import com.almox.model.entidades.PessoaJuridica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class PessoaJuridicaDTO<E extends PessoaJuridica, DTO> extends EntidadeDTO<E, DTO> {

    private String razaoSocial;
    private String cnpj;
    private String nomeFantasia;
    private ContatoDTO contato;


    public PessoaJuridicaDTO(E entidade) {
        razaoSocial = entidade.getRazaoSocial();
        cnpj = entidade.getCnpj();
        nomeFantasia = entidade.getNomeFantasia();
        contato = new ContatoDTO(entidade.getContato());

    }

}
