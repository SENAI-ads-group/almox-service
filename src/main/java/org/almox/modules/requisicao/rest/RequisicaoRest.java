package org.almox.modules.requisicao.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.requisicao.dto.CriarRequisicaoDTO;
import org.almox.modules.requisicao.dto.FiltroRequisicao;
import org.almox.modules.requisicao.dto.RequisicaoDTO;
import org.almox.modules.requisicao.dto.RequisicaoEntregueDTO;
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

import java.net.URI;
import java.util.Optional;
import java.util.Set;
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
        Requisicao requisicaoCriada = requisicaoService.criar(requisicaoMapper.toRequisicao(dto));
        URI uriRequisicaoCriada = getUriCriado(requisicaoCriada.getId());
        return ResponseEntity.created(uriRequisicaoCriada).build();
    }

    @Override
    public ResponseEntity<RequisicaoDTO> alterarItensRequisicao(UUID id, Set<CriarRequisicaoDTO.Item> dto) {
        Requisicao requisicaoComItensAlterados = requisicaoService.alterarItens(id, requisicaoMapper.toItemRequisicao(dto));
        return ResponseEntity.ok(requisicaoMapper.toDTO(requisicaoComItensAlterados));
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        requisicaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> atenderRequisicao(UUID id) {
        requisicaoService.atenderRequisicao(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<Void> cancelarRequisicao(UUID id) {
        requisicaoService.cancelarRequisicao(id);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<RequisicaoEntregueDTO> entregarAtendimento(UUID id) {
        String codigoConfirmacao = requisicaoService.entregarRequisicao(id);
        return ResponseEntity.accepted().body(new RequisicaoEntregueDTO(codigoConfirmacao));
    }

    @Override
    public ResponseEntity<Void> confirmarRecebimentoRequisicao(String codigoConfirmacao) {
        requisicaoService.confirmarRecebimento(codigoConfirmacao);
        return ResponseEntity.accepted().build();
    }
}
