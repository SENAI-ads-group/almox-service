package com.almox.modules.requisicao;

import com.almox.modules.crud.CrudRest;
import com.almox.modules.crud.ICrudService;
import com.almox.modules.requisicao.model.FiltroRequisicaoDTO;
import com.almox.modules.requisicao.model.Requisicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/atender/{id}")
    public void atenderRequisicao(@PathVariable("id") Long id) {
        service.atenderRequisicao(id);
    }

    @PostMapping(value = "/cancelar/{id}")
    public void cancelarRequisicao(@PathVariable("id") Long id) {
        service.cancelarRequisicao(id);
    }

    @PostMapping(value = "/entregar/{id}")
    public void entregarAtendimento(@PathVariable("id") Long id, @RequestBody Requisicao requisicaoEntregue) {
        service.entregarRequisicao(id, requisicaoEntregue);
    }

}
