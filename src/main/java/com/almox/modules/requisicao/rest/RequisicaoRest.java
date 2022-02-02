package com.almox.modules.requisicao.rest;

import com.almox.modules.common.CrudRest;
import com.almox.modules.common.ICrudService;
import com.almox.modules.requisicao.service.RequisicaoService;
import com.almox.modules.requisicao.model.FiltroRequisicaoDTO;
import com.almox.modules.requisicao.model.Requisicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/requisicoes")
public class RequisicaoRest extends CrudRest<Requisicao, FiltroRequisicaoDTO> {

    private final RequisicaoService service;

    @Autowired
    public RequisicaoRest(RequisicaoService service) {
        this.service = service;
    }

    @Override
    public ICrudService<Requisicao, FiltroRequisicaoDTO> getService() {
        return service;
    }
}
