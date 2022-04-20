package org.almox.modules.requisicao.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.requisicao.dto.CriarRequisicaoDTO;
import org.almox.modules.requisicao.dto.FiltroRequisicao;
import org.almox.modules.requisicao.dto.RequisicaoDTO;
import org.almox.modules.requisicao.dto.RequisicaoMapper;
import org.almox.modules.requisicao.model.Requisicao;
import org.almox.modules.requisicao.model.StatusRequisicao;
import org.almox.modules.requisicao.service.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequisicaoRest implements RequisicaoRestFacade {

    private final RequisicaoService requisicaoService;
    private final RequisicaoMapper requisicaoMapper;

    @Override
    public ResponseEntity<RestCollection<RequisicaoDTO>> buscar(
            StatusRequisicao status, Optional<Integer> page, Optional<Integer> size, String[] sort
    ) {
        Pageable paginacao = criarPaginacao(page, size, sort);
        FiltroRequisicao filtro = new FiltroRequisicao();
        filtro.status = status;
        Page<RequisicaoDTO> requisicaoPage = requisicaoService.buscar(filtro, paginacao).map(requisicaoMapper::toDTO);
        return ResponseEntity.ok(RestCollection.fromPage(requisicaoPage));
    }

    @Override
    public ResponseEntity<RequisicaoDTO> buscarPorId(UUID id) {
        RequisicaoDTO requisicaoDTOEncontrada = requisicaoMapper.toDTO(requisicaoService.buscarPorId(id));
        return ResponseEntity.ok(requisicaoDTOEncontrada);
    }

    @Override
    public ResponseEntity<Void> criar(CriarRequisicaoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> atenderRequisicao(@PathVariable("id") UUID id) {
        requisicaoService.atenderRequisicao(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Void> cancelarRequisicao(@PathVariable("id") UUID id) {
        requisicaoService.cancelarRequisicao(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Void> entregarAtendimento(@PathVariable("id") UUID id, @RequestBody Requisicao requisicaoEntregue) {
        requisicaoService.entregarRequisicao(id, requisicaoEntregue);
        return ResponseEntity.accepted().build();
    }
}
