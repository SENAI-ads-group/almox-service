package org.almox.modules.requisicao.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.requisicao.dto.CriarRequisicaoDTO;
import org.almox.modules.requisicao.dto.RequisicaoDTO;
import org.almox.modules.requisicao.model.StatusRequisicao;
import org.almox.modules.requisicao.service.RequisicaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RequisicaoRest implements RequisicaoRestFacade {

    private final RequisicaoServiceImpl requisicaoService;

    @Override
    public ResponseEntity<RestCollection<RequisicaoDTO>> buscar(
            StatusRequisicao status, Optional<Integer> page, Optional<Integer> size, String[] sort
    ) {
        return null;
    }

    @Override
    public ResponseEntity<RequisicaoDTO> buscarPorId(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> criar(CriarRequisicaoDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        return null;
    }
}
