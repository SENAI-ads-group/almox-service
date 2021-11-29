package com.almox.api.controllers;

import com.almox.model.dto.FiltroRequisicaoDTO;
import com.almox.model.entidades.Requisicao;
import com.almox.services.ICrudService;
import com.almox.services.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/requisicoes")
public class RequisicaoController extends CrudController<Requisicao, FiltroRequisicaoDTO> {

    private final RequisicaoService service;

    @Autowired
    public RequisicaoController(RequisicaoService service) {
        this.service = service;
    }

    @Override
    public ICrudService<Requisicao, FiltroRequisicaoDTO> getService() {
        return service;
    }
}
