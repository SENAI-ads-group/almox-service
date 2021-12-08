package com.almox.api.controllers;

import com.almox.model.dto.FiltroPedidoDTO;
import com.almox.model.entidades.Requisicao;
import com.almox.model.entidades.pedido.Pedido;
import com.almox.services.ICrudService;
import com.almox.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController extends CrudController<Pedido, FiltroPedidoDTO> {

    private final PedidoService service;

    @Autowired
    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @Override
    public ICrudService<Pedido, FiltroPedidoDTO> getService() {
        return service;
    }

    @PostMapping(value = "/cancelar/{id}")
    public void cancelarPedido(@PathVariable("id") Long id) {
        service.cancelarPedido(id);
    }

    @PostMapping(value = "/receber/{id}")
    public void receberPedido(@PathVariable("id") Long id, @RequestBody @Valid Pedido pedidoEntregue) {
        service.receberPedido(id, pedidoEntregue);
    }
}
