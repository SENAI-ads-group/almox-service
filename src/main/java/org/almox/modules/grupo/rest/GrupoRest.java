package org.almox.modules.grupo.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.departamento.dto.DepartamentoDTO;
import org.almox.modules.grupo.dto.FiltroGrupo;
import org.almox.modules.grupo.dto.GrupoDTO;
import org.almox.modules.grupo.dto.GrupoMapper;
import org.almox.modules.grupo.model.Grupo;
import org.almox.modules.grupo.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GrupoRest implements GrupoRestFacade {

    private final GrupoService grupoService;
    private final GrupoMapper grupoMapper;

    @Override
    public ResponseEntity<RestCollection<GrupoDTO>> buscar(String descricao, Optional<Integer> page, Optional<Integer> size, String[] sort) {
        Pageable paginacao = criarPaginacao(page, size, sort);
        FiltroGrupo filtro = FiltroGrupo.builder()
                .descricao(descricao)
                .build();

        Page<GrupoDTO> grupoPage = grupoService.buscar(filtro, paginacao).map(grupoMapper::toDTO);
        return ResponseEntity.ok(RestCollection.fromPage(grupoPage));
    }

    @Override
    public ResponseEntity<RestCollection<GrupoDTO>> buscarExcluidos(String descricao, Optional<Integer> page, Optional<Integer> size, String[] sort) {
        Pageable paginacao = criarPaginacao(page, size, sort);
        FiltroGrupo filtro = FiltroGrupo.builder()
                .descricao(descricao)
                .build();

        Page<GrupoDTO> grupoPage = grupoService.buscarExcluidos(filtro, paginacao).map(grupoMapper::toDTO);
        return ResponseEntity.ok(RestCollection.fromPage(grupoPage));
    }

    @Override
    public ResponseEntity<GrupoDTO> buscarPorId(UUID id) {
        Grupo grupoEncontrado = grupoService.buscarPorId(id);
        GrupoDTO dto = grupoMapper.toDTO(grupoEncontrado);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> criar(GrupoDTO dto) {
        Grupo departamentoCriado = grupoService.criar(grupoMapper.fromDTO(dto));
        URI uriCriado = getUriCriado(departamentoCriado.getId());
        return ResponseEntity.created(uriCriado).build();
    }

    @Override
    public ResponseEntity<GrupoDTO> atualizar(UUID id, GrupoDTO dto) {
        Grupo departamentoAtualizado = grupoService.atualizar(id, grupoMapper.fromDTO(dto));
        GrupoDTO departamentoAtualizadoDTO = grupoMapper.toDTO(departamentoAtualizado);
        return ResponseEntity.ok(departamentoAtualizadoDTO);
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        grupoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
