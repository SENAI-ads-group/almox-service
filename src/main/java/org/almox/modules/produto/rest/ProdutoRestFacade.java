package org.almox.modules.produto.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.almox.core.rest.RestCollection;
import org.almox.modules.auditoria.FiltroStatusAuditavel;
import org.almox.modules.produto.dto.AtualizarProdutoDTO;
import org.almox.modules.produto.dto.CriarProdutoDTO;
import org.almox.modules.produto.dto.ProdutoDTO;
import org.almox.modules.produto.model.UnidadeMedida;
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

@Tag(name = "Produtos", description = "Operações relacionadas aos recursos de produtos")
@RequestMapping(ProdutoRestFacade.PATH)
public interface ProdutoRestFacade {
    String PATH = "/produtos";

    @GetMapping
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "descricao",
                    description = "Descrição do produto",
                    schema = @Schema(type = "string")
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "codigoBarras",
                    description = "Código de barras do produto",
                    schema = @Schema(type = "string")
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "unidadeMedida",
                    description = "Unidade de medida do produto",
                    schema = @Schema(implementation = UnidadeMedida.class)
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "status",
                    schema = @Schema(implementation = FiltroStatusAuditavel.class)
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<ProdutoDTO>> buscar(
            @RequestParam(required = false) String cnpj,
            @RequestParam(required = false, defaultValue = "") String nome,
            @RequestParam(required = false, defaultValue = "CONSIDERAR_TODOS") FiltroStatusAuditavel status,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @GetMapping("/{id}")
    ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable("id") UUID id);

    @PostMapping
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = CriarProdutoDTO.class))
            )
    )
    ResponseEntity<Void> criar(@RequestBody CriarProdutoDTO dto);

    @PutMapping("/{id}")
    ResponseEntity<ProdutoDTO> atualizar(@PathVariable("id") UUID id, @RequestBody AtualizarProdutoDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);
}
