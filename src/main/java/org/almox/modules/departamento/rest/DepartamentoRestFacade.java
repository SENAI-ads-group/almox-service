package org.almox.modules.departamento.rest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.almox.core.rest.RestCollection;
import org.almox.core.rest.RestInterface;
import org.almox.modules.auditoria.FiltroStatusAuditavel;
import org.almox.modules.departamento.model.DepartamentoDTO;
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

@RequestMapping(DepartamentoRestFacade.PATH)
public interface DepartamentoRestFacade extends RestInterface {
    String PATH = "/departamentos";

    @GetMapping
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "nome",
                    description = "Nome do departamento",
                    schema = @Schema(type = "string")
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<DepartamentoDTO>> buscar(
            @RequestParam(required = false, defaultValue = "") String nome,
            @RequestParam(required = false, defaultValue = "CONSIDERAR_TODOS") FiltroStatusAuditavel status,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @GetMapping("/{id}")
    ResponseEntity<DepartamentoDTO> buscarPorId(@PathVariable("id") UUID id);

    @PostMapping
    ResponseEntity<Void> criar(@RequestBody DepartamentoDTO dto);

    @PutMapping("/{id}")
    ResponseEntity<DepartamentoDTO> atualizar(@PathVariable("id") UUID id, @RequestBody DepartamentoDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);
}
