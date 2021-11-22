package com.almox.services.impl;

import com.almox.model.entidades.Requisicao;
import com.almox.services.IRequisicaoService;
import org.springframework.stereotype.Service;

@Service
public class RequisicaoService implements IRequisicaoService {

    @Override
    public Requisicao criar(Requisicao entidade) {
        System.out.println(entidade.getDepartamento().getNome());
        return entidade;
    }
}
