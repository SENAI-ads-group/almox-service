package com.almox.modules.departamento;

import com.almox.modules.crud.CrudRest;
import com.almox.modules.crud.ICrudService;
import com.almox.modules.departamento.model.Departamento;
import com.almox.modules.departamento.model.FiltroDepartamentoDTO;
import com.almox.modules.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoRest extends CrudRest<Departamento, FiltroDepartamentoDTO> {

    private final DepartamentoService service;
    private final UsuarioService usuarioService;

    @Autowired
    public DepartamentoRest(DepartamentoService service, UsuarioService usuarioService) {
        this.service = service;
        this.usuarioService = usuarioService;
    }

    @Override
    public ICrudService<Departamento, FiltroDepartamentoDTO> getService() {
        return service;
    }

    @GetMapping("/associados-usuario-logado")
    public ResponseEntity<List<Departamento>> buscarAssociadosUsuarioLogado(Principal principal) {
        return ResponseEntity.ok(service.buscarAssociadosUsuarioLogado());
    }

    @GetMapping("/relacao-produto/{idProduto}")
    public ResponseEntity<List<Departamento>> buscarPorRelacaoProduto(@PathVariable("idProduto") Long idProduto,
                                                                      @RequestParam(value = "relacionados", defaultValue = "true") boolean relacionados) {
        return ResponseEntity.ok(service.buscarPorRelacaoComProduto(idProduto, relacionados));
    }
}