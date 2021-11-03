package com.almox.api.controllers;

import com.almox.model.entidades.Auditavel;
import com.almox.model.entidades.EntidadePadrao;
import com.almox.services.ICrudService;
import com.almox.services.IUsuarioService;
import com.almox.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Component
public abstract class CrudController<ENTIDADE extends EntidadePadrao, FILTRO> {

    @Autowired
    private IUsuarioService usuarioService;

    public abstract ICrudService<ENTIDADE, FILTRO> getService();

    @GetMapping("listar")
    public ResponseEntity<List<ENTIDADE>> listar() {
        var resultadoListagem = getService().buscarTodos();
        return ResponseEntity.ok(resultadoListagem);
    }

    @PostMapping("listar")
    public ResponseEntity<List<ENTIDADE>> listar(@RequestBody FILTRO filtro) {
        var resultadoListagem = getService().buscarTodos(filtro);
        return ResponseEntity.ok(resultadoListagem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ENTIDADE> buscarPorId(@PathVariable Long id) {
        var entidadeEncontrada = getService().buscarPorId(id);
        return ResponseEntity.ok().body(entidadeEncontrada);
    }

    @PostMapping
    public ResponseEntity<ENTIDADE> criar(@Valid @RequestBody ENTIDADE entidade) {
        if (entidade instanceof Auditavel) { //força os dados de criação do usuário
            var auditavel = (Auditavel) entidade;
            auditavel.setCriadoPor(usuarioService.getCurrentAuditor().orElse(null));
            auditavel.setDataCriacao(LocalDateTime.now());
            auditavel.setDataAlteracao(LocalDateTime.now());
        }

        var entidadeCriada = getService().criar(entidade);
        var uriEntidadeCriada = ControllerUtil.getUriCriado(entidadeCriada);
        return ResponseEntity.created(uriEntidadeCriada).body(entidadeCriada);
    }

    @PutMapping("{id}")
    public ResponseEntity<ENTIDADE> editar(@PathVariable Long id, @RequestBody ENTIDADE entidade) {
        if (entidade instanceof Auditavel) { //força os dados de atualização do usuário
            var auditavel = (Auditavel) entidade;
            auditavel.setDataAlteracao(LocalDateTime.now());
            auditavel.setAlteradoPor(usuarioService.getCurrentAuditor().orElse(null));
        }

        var entidadeAtualizada = getService().atualizar(id, entidade);
        return ResponseEntity.ok().body(entidadeAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        getService().excluir(id);
        return ResponseEntity.noContent().build();
    }
}
