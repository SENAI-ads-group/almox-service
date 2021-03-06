package com.almox.modules.produto;

import com.almox.modules.crud.CrudRest;
import com.almox.modules.produto.model.FiltroProdutoDTO;
import com.almox.modules.produto.model.HistoricoEstoqueProduto;
import com.almox.modules.produto.model.Produto;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoRest extends CrudRest<Produto, FiltroProdutoDTO> {

    @Getter
    private final ProdutoService service;

    @Autowired
    public ProdutoRest(ProdutoService produtoService) {
        this.service = produtoService;
    }

    @GetMapping("/historicos-estoque/{id}")
    public ResponseEntity<List<HistoricoEstoqueProduto>> buscarHistoricosEstoque(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarHistoricosEstoque(id));
    }
}
