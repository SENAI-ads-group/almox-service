package com.almox.modules.pedido;

import com.almox.modules.crud.CrudRest;
import com.almox.modules.crud.ICrudService;
import com.almox.modules.pedido.model.FiltroPedidoDTO;
import com.almox.modules.pedido.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoRest extends CrudRest<Pedido, FiltroPedidoDTO> {

    private final PedidoService service;

    @Autowired
    public PedidoRest(PedidoService service) {
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
