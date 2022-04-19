package org.almox.modules.grupo.rest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.almox.core.rest.RestCollection;
import org.almox.core.rest.RestInterface;
import org.almox.modules.grupo.dto.GrupoDTO;
import org.almox.modules.operador.Funcoes;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Tag(name = "Grupos", description = "Operações relacionadas aos recursos de grupos")
@RequestMapping(value = GrupoRestFacade.PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public interface GrupoRestFacade extends RestInterface {
    String PATH = "/grupos";

    @GetMapping
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "descricao",
                    description = "Descrição do grupo",
                    schema = @Schema(type = "string")
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<GrupoDTO>> buscar(
            @RequestParam(required = false, defaultValue = "") String nome,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "descricao") String[] sort
    );

    @Funcoes.REQUISITANTE
    @GetMapping("/excluidos")
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "descricao",
                    description = "Descrição do grupo",
                    schema = @Schema(type = "string")
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<GrupoDTO>> buscarExcluidos(
            @RequestParam(required = false, defaultValue = "") String descricao,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @GetMapping("/{id}")
    ResponseEntity<GrupoDTO> buscarPorId(@PathVariable("id") UUID id);

    @Funcoes.ALMOXARIFE_ADMINISTRADOR
    @PostMapping
    ResponseEntity<Void> criar(@RequestBody GrupoDTO dto);

    @Funcoes.ALMOXARIFE_ADMINISTRADOR
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<GrupoDTO> atualizar(@PathVariable("id") UUID id, @RequestBody GrupoDTO dto);

    @Funcoes.ADMINISTRADOR
    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);
}
