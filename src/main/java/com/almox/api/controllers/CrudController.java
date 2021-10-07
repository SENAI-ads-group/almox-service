package com.almox.api.controllers;

import com.almox.model.dto.EntidadeDTO;
import com.almox.model.entidades.Auditavel;
import com.almox.model.entidades.EntidadePadrao;
import com.almox.model.entidades.Usuario;
import com.almox.model.enums.TipoUsuario;
import com.almox.services.ICrudService;
import com.almox.util.ControllerUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

public abstract class CrudController<ENTIDADE extends EntidadePadrao, FILTRO, DTO extends EntidadeDTO<ENTIDADE, DTO>> {

    private static Usuario usuarioMock = new Usuario(1L, "Sistema", "usuario@almox.com", TipoUsuario.ADMINISTRADOR, "123"); //MOCK

    public abstract ICrudService<ENTIDADE, FILTRO> getService();

    public abstract DTO getDTO();

    @GetMapping("listar")
    public ResponseEntity<List<DTO>> listar() {
        var entidadeList = getService().buscarTodos();
        var dtoList = getDTO().entidadeListParaDTOList(entidadeList);

        return ResponseEntity.ok(dtoList);
    }

    @PostMapping("listar")
    public ResponseEntity<List<DTO>> listar(@RequestBody FILTRO filtro) {
        var entidadeList = getService().buscarTodos(filtro);
        var dtoList = getDTO().entidadeListParaDTOList(entidadeList);

        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> buscarPorId(@PathVariable Long id) {
        var entidadeEncontrada = getService().buscarPorId(id);
        var entidadeDTO = getDTO().entidadeParaDTO(entidadeEncontrada);

        return ResponseEntity.ok().body(entidadeDTO);
    }

    @PostMapping
    public ResponseEntity<DTO> criar(@Valid @RequestBody ENTIDADE entidade) {
        if (entidade instanceof Auditavel) { //força os dados de criação do usuário
            var auditavel = (Auditavel) entidade;
            auditavel.setDataCriacao(LocalDateTime.now());
            auditavel.setCriadoPor(usuarioMock);
            auditavel.setDataAlteracao(LocalDateTime.now());
            auditavel.setAlteradoPor(usuarioMock);
        }

        var entidadeCriada = getService().criar(entidade);
        var uriEntidadeCriada = ControllerUtil.getUriCriado(entidadeCriada);
        var entidadeDTO = getDTO().entidadeParaDTO(entidade);
        return ResponseEntity.created(uriEntidadeCriada).body(entidadeDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<DTO> editar(@PathVariable Long id, @RequestBody DTO entidadeDTO) {
        var entidade = getDTO().dtoParaEntidade(entidadeDTO);
        if (entidade instanceof Auditavel) { //força os dados de atualização do usuário
            var auditavel = (Auditavel) entidade;
            auditavel.setAlteradoPor(usuarioMock);
            auditavel.setDataAlteracao(LocalDateTime.now());
        }

        var entidadeAtualizada = getService().atualizar(id, entidade);
        var entidadeDTOAtualizada = getDTO().entidadeParaDTO(entidadeAtualizada);

        return ResponseEntity.ok().body(entidadeDTOAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        getService().excluir(id);
        return ResponseEntity.noContent().build();
    }
}
