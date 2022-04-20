package org.almox.modules.produto.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.almox.core.rest.RestCollection;
import org.almox.core.rest.RestInterface;
import org.almox.modules.operador.Funcoes;
import org.almox.modules.produto.dto.AtualizarProdutoDTO;
import org.almox.modules.produto.dto.CriarProdutoDTO;
import org.almox.modules.produto.dto.HistoricoEstoqueDTO;
import org.almox.modules.produto.dto.ProdutoDTO;
import org.almox.modules.produto.model.UnidadeMedida;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Tag(name = "Produtos", description = "Operações relacionadas aos recursos de produtos")
@RequestMapping(ProdutoRestFacade.PATH)
public interface ProdutoRestFacade extends RestInterface {
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
                    name = "idGrupos",
                    description = "ID dos grupos a qual o produto pode pertencer",
                    array = @ArraySchema(schema = @Schema(implementation = UUID.class))
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "idDepartamentos",
                    description = "ID dos departamentos associados ao produto",
                    array = @ArraySchema(schema = @Schema(implementation = UUID.class))
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "idFornecedor",
                    description = "ID do fornecedor do produto",
                    schema = @Schema(implementation = UUID.class)
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "unidadeMedida",
                    description = "Unidade de medida do produto",
                    schema = @Schema(implementation = UnidadeMedida.class)
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<ProdutoDTO>> buscar(
            @RequestParam(required = false) String cnpj,
            @RequestParam(required = false, defaultValue = "") String descricao,
            @RequestParam(required = false) String codigoBarras,
            @RequestParam(required = false) Set<UUID> idGrupos,
            @RequestParam(required = false) Set<UUID> idDepartamentos,
            @RequestParam(required = false) UUID idFornecedor,
            @RequestParam(required = false) UnidadeMedida unidadeMedida,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @Funcoes.ADMINISTRADOR
    @GetMapping("/excluidos")
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
                    name = "idGrupos",
                    description = "ID dos grupos a qual o produto pode pertencer",
                    array = @ArraySchema(schema = @Schema(implementation = UUID.class))
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "idDepartamentos",
                    description = "ID dos departamentos associados ao produto",
                    array = @ArraySchema(schema = @Schema(implementation = UUID.class))
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "idFornecedor",
                    description = "ID do fornecedor do produto",
                    schema = @Schema(implementation = UUID.class)
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "unidadeMedida",
                    description = "Unidade de medida do produto",
                    schema = @Schema(implementation = UnidadeMedida.class)
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<ProdutoDTO>> buscarExcluidos(
            @RequestParam(required = false) String cnpj,
            @RequestParam(required = false, defaultValue = "") String descricao,
            @RequestParam(required = false) String codigoBarras,
            @RequestParam(required = false) Set<UUID> idGrupos,
            @RequestParam(required = false) Set<UUID> idDepartamentos,
            @RequestParam(required = false) UUID idFornecedor,
            @RequestParam(required = false) UnidadeMedida unidadeMedida,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @GetMapping("/{id}")
    ResponseEntity<ProdutoDTO> buscarPorId(@PathVariable("id") UUID id);

    @Funcoes.ALMOXARIFE_ADMINISTRADOR
    @GetMapping("/{id}/historico-estoque")
    ResponseEntity<List<HistoricoEstoqueDTO>> buscarHistoricosEstoque(@PathVariable("id") UUID id);

    @Funcoes.ALMOXARIFE_ADMINISTRADOR
    @PostMapping
    @Operation(
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = CriarProdutoDTO.class))
            )
    )
    ResponseEntity<Void> criar(@RequestBody CriarProdutoDTO dto);

    @Funcoes.ALMOXARIFE_ADMINISTRADOR
    @PutMapping("/{id}")
    ResponseEntity<ProdutoDTO> atualizar(@PathVariable("id") UUID id, @RequestBody AtualizarProdutoDTO dto);

    @Funcoes.ADMINISTRADOR
    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);
}
