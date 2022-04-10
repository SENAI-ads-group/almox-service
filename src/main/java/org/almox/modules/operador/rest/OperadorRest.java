package org.almox.modules.operador.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.operador.dto.OperadorDTO;
import org.almox.modules.operador.dto.OperadorFiltroDTO;
import org.almox.modules.operador.dto.OperadorMapper;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.service.OperadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OperadorRest implements OperadorRestFacade {

    private final OperadorService operadorService;
    private final OperadorMapper operadorMapper;

    @Override
    public ResponseEntity<RestCollection<OperadorDTO>> buscar(String nome, String email, Optional<Integer> page, Optional<Integer> size, String[] sort) {
        Sort ordenacao = Sort.by(sort);
        Pageable paginacao = PageRequest.of(page.orElse(0), size.orElse(15), ordenacao);
        OperadorFiltroDTO filtro = OperadorFiltroDTO.builder().nome(nome).email(email).build();

        List<Operador> operadoresList = page.isEmpty()
                ? operadorService.buscarPaginado(filtro, paginacao).getContent()
                : operadorService.buscar(filtro, ordenacao);

        RestCollection<OperadorDTO> operadoresDTO = new RestCollection<>(operadoresList, paginacao).mapCollection(operadorMapper::toDTO);
        return ResponseEntity.ok(operadoresDTO);
    }

    @Override
    public ResponseEntity<OperadorDTO> buscarPorId(UUID id) {
        Operador operador = operadorService.buscarPorId(id);
        OperadorDTO dto = operadorMapper.toDTO(operador);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<OperadorDTO> buscarPorLogin(String login) {
        Operador operador = operadorService.buscarPorLogin(login);
        OperadorDTO dto = operadorMapper.toDTO(operador);
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> criar(OperadorDTO dto) {
        Operador operadorCriado = operadorService.criar(operadorMapper.fromDTO(dto));
        URI uriCriado = getUriCriado(operadorCriado.getId());
        return ResponseEntity.created(uriCriado).build();
    }

    @Override
    public ResponseEntity<OperadorDTO> atualizar(UUID id, OperadorDTO dto) {
        Operador operador = operadorMapper.fromDTO(dto);
        OperadorDTO dtoAtualizado = operadorMapper.toDTO(operadorService.atualizar(id, operador));
        return ResponseEntity.ok(dtoAtualizado);
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        operadorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
