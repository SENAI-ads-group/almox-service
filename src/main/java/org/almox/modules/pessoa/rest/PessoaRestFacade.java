package org.almox.modules.pessoa.rest;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.almox.core.rest.RestCollection;
import org.almox.core.rest.RestInterface;
import org.almox.modules.pessoa.dto.PessoaDTO;
import org.almox.modules.pessoa.dto.PessoaFisicaDTO;
import org.almox.modules.pessoa.dto.PessoaJuridicaDTO;
import org.almox.modules.pessoa.model.TipoPessoa;
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

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Tag(name = "Pessoas", description = "Operações relacionadas aos recursos de pessoas")
@RequestMapping("/pessoas")
public interface PessoaRestFacade extends RestInterface {
    @GetMapping
    @Parameters({
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "tipo",
                    description = "Tipo de pessoa",
                    schema = @Schema(implementation = TipoPessoa.class)
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "nome",
                    description = "Nome das pessoas",
                    schema = @Schema(type = "string")
            ),
            @Parameter(
                    in = ParameterIn.QUERY,
                    name = "email",
                    description = "Email das pessoas",
                    schema = @Schema(type = "string")
            )
    })
    @PageableAsQueryParam
    ResponseEntity<RestCollection<PessoaDTO>> buscar(
            @RequestParam(required = false) TipoPessoa tipo,
            @RequestParam(required = false, defaultValue = "") String nome,
            @RequestParam(required = false, defaultValue = "") String email,
            @RequestParam(required = false) Optional<Integer> page,
            @RequestParam(required = false) Optional<Integer> size,
            @RequestParam(required = false, defaultValue = "id") String[] sort
    );

    @GetMapping("/{id}")
    ResponseEntity<PessoaDTO> buscarPorId(@PathVariable("id") UUID id);

    @PostMapping("/pf")
    ResponseEntity<Void> criarPessoaFisica(@RequestBody PessoaFisicaDTO dto);

    @PostMapping("/pj")
    ResponseEntity<Void> criarPessoaJuridica(@RequestBody @Valid PessoaJuridicaDTO dto);

    @PutMapping("/pf/{id}")
    ResponseEntity<PessoaFisicaDTO> atualizarPessoaFisica(@PathVariable("id") UUID id, @RequestBody @Valid PessoaFisicaDTO dto);

    @PutMapping("/pj/{id}")
    ResponseEntity<PessoaJuridicaDTO> atualizarPessoaJuridica(@PathVariable("id") UUID id, @RequestBody @Valid PessoaJuridicaDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> excluir(@PathVariable("id") UUID id);
}
