package org.almox.modules.requisicao.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.almox.core.rest.RestCollection;
import org.almox.modules.requisicao.dto.CriarRequisicaoDTO;
import org.almox.modules.requisicao.dto.RequisicaoDTO;
import org.almox.modules.requisicao.model.StatusRequisicao;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Tag(name = "Requisições", description = "Operações relacionadas aos recursos de requisições")
@RequestMapping(RequisicaoRestFacade.PATH)
public interface RequisicaoRestFacade {
    String PATH = "/requisicoes";

    @GetMapping
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "status",
                    schema = @Schema(implementation = StatusRequisicao.class)
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<RequisicaoDTO>> buscar(
            @RequestParam(required = false, defaultValue = "AGUARDANDO_ATENDIMENTO") StatusRequisicao status,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @GetMapping("/{id}")
    ResponseEntity<RequisicaoDTO> buscarPorId(@PathVariable("id") UUID id);

    @PostMapping
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = CriarRequisicaoDTO.class))
            )
    )
    ResponseEntity<Void> criar(@RequestBody CriarRequisicaoDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);
}
