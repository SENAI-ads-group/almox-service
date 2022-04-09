package org.almox.modules.operador.rest;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.almox.core.rest.RestCollection;
import org.almox.core.rest.RestInterface;
import org.almox.modules.operador.dto.OperadorDTO;
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

@RequestMapping("/operadores")
public interface OperadorRestFacade extends RestInterface {
    @GetMapping
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "paginado",
                    description = "Ativa a paginação",
                    schema = @Schema(
                            type = "boolean",
                            defaultValue = "true"
                    )
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<OperadorDTO>> buscar(
            @RequestParam(required = false, defaultValue = "") String nome,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @GetMapping("/{id}")
    ResponseEntity<OperadorDTO> buscarPorId(@PathVariable("id") UUID id);

    @GetMapping("/login/{login}")
    ResponseEntity<OperadorDTO> buscarPorLogin(@PathVariable("login") String login);

    @PostMapping
    ResponseEntity<Void> criar(@RequestBody OperadorDTO dto);

    @PutMapping("/{id}")
    ResponseEntity<OperadorDTO> atualizar(@PathVariable("id") UUID id, @RequestBody OperadorDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);
}
