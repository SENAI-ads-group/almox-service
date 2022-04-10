package org.almox.modules.grupo.rest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.almox.core.rest.RestCollection;
import org.almox.core.rest.RestInterface;
import org.almox.modules.auditoria.FiltroStatusAuditavel;
import org.almox.modules.grupo.model.GrupoDTO;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Tag(name = "Grupos", description = "Operações relacionadas aos recursos de grupos")
@RequestMapping(GrupoRestFacade.PATH)
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
            @RequestParam(required = false, defaultValue = "CONSIDERAR_TODOS") FiltroStatusAuditavel status,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "descricao") String[] sort
    );

    @GetMapping("/{id}")
    ResponseEntity<GrupoDTO> buscarPorId(@PathVariable("id") UUID id);

    @PostMapping
    ResponseEntity<Void> criar(@RequestBody GrupoDTO dto);

    @PutMapping("/{id}")
    ResponseEntity<GrupoDTO> atualizar(@PathVariable("id") UUID id, @RequestBody GrupoDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);
}
