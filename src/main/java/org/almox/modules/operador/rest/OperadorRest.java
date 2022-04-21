package org.almox.modules.operador.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.operador.dto.FiltroOperador;
import org.almox.modules.operador.dto.OperadorDTO;
import org.almox.modules.operador.dto.OperadorMapper;
import org.almox.modules.operador.dto.RecuperarEmailDTO;
import org.almox.modules.operador.model.Operador;
import org.almox.modules.operador.service.OperadorService;
import org.almox.modules.operador.service.RecuperacaoSenhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private final RecuperacaoSenhaService recuperacaoSenhaService;

    @Override
    public ResponseEntity<RestCollection<OperadorDTO>> buscar(String nome, String email, Optional<Integer> page, Optional<Integer> size, String[] sort) {
        Pageable paginacao = criarPaginacao(page, size, sort);
        FiltroOperador filtro = FiltroOperador.builder().nome(nome).email(email).build();

        Page<OperadorDTO> operadoresPage = operadorService.buscar(filtro, paginacao).map(operadorMapper::toDTO);
        return ResponseEntity.ok(RestCollection.fromPage(operadoresPage));
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
    public ResponseEntity<RecuperarEmailDTO.Resposta> recuperarEmail(RecuperarEmailDTO.Requisicao requisicaoRecuperarEmail) {
        RecuperarEmailDTO.Resposta respostaRecuperacaoEmail = recuperacaoSenhaService.recuperarEmail(requisicaoRecuperarEmail);
        return ResponseEntity.ok(respostaRecuperacaoEmail);
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
