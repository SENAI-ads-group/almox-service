package org.almox.modules.departamento.rest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.almox.core.rest.RestCollection;
import org.almox.core.rest.RestInterface;
import org.almox.modules.departamento.dto.CriarDepartamentoDTO;
import org.almox.modules.departamento.dto.DepartamentoDTO;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.MediaType;
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

@Tag(name = "Departamentos", description = "Operações relacionadas aos recursos de departamentos")
@RequestMapping(DepartamentoRestFacade.PATH)
public interface DepartamentoRestFacade extends RestInterface {
    String PATH = "/departamentos";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "descricao",
                    description = "Descrição do departamento",
                    schema = @Schema(type = "string")
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<DepartamentoDTO>> buscar(
            @RequestParam(required = false, defaultValue = "") String descricao,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @GetMapping(value = "/excluidos", produces = MediaType.APPLICATION_JSON_VALUE)
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "descricao",
                    description = "Descrição do departamento",
                    schema = @Schema(type = "string")
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<DepartamentoDTO>> buscarExcluidos(
            @RequestParam(required = false, defaultValue = "") String descricao,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DepartamentoDTO> buscarPorId(@PathVariable("id") UUID id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> criar(@RequestBody CriarDepartamentoDTO dto);

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DepartamentoDTO> atualizar(@PathVariable("id") UUID id, @RequestBody DepartamentoDTO dto);

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);
}
