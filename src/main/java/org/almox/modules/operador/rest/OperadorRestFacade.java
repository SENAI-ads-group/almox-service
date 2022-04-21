package org.almox.modules.operador.rest;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.almox.core.rest.RestCollection;
import org.almox.core.rest.RestInterface;
import org.almox.modules.operador.Funcoes;
import org.almox.modules.operador.dto.OperadorDTO;
import org.almox.modules.operador.dto.RecuperarEmailDTO;
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

@Tag(name = "Operadores", description = "Operações relacionadas aos recursos de operadores")
@RequestMapping("/operadores")
public interface OperadorRestFacade extends RestInterface {
    @GetMapping
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "nome",
                    description = "Nome da pessoa associadda",
                    schema = @Schema(type = "string")
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "email",
                    description = "Email da pessoa associada",
                    schema = @Schema(type = "string")
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

    @PostMapping("/recuperar-email")
    ResponseEntity<RecuperarEmailDTO.Resposta> recuperarEmail(@RequestBody RecuperarEmailDTO.Requisicao requisicaoRecuperarEmail);

    @Funcoes.ADMINISTRADOR
    @PostMapping
    ResponseEntity<Void> criar(@RequestBody OperadorDTO dto);

    @Funcoes.ADMINISTRADOR
    @PutMapping("/{id}")
    ResponseEntity<OperadorDTO> atualizar(@PathVariable("id") UUID id, @RequestBody OperadorDTO dto);

    @Funcoes.ADMINISTRADOR
    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);
}
