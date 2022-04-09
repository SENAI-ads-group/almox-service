package org.almox.modules.produto.rest;

import lombok.RequiredArgsConstructor;
import org.almox.core.rest.RestCollection;
import org.almox.modules.auditoria.FiltroStatusAuditavel;
import org.almox.modules.fornecedor.model.FornecedorDTO;
import org.almox.modules.produto.service.ProdutoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProdutoRest implements ProdutoRestFacade {

    private final ProdutoServiceImpl fornecedorService;

    @Override
    public ResponseEntity<RestCollection<FornecedorDTO>> buscar(
            String cnpj, String nome, FiltroStatusAuditavel status,
            Optional<Integer> page, Optional<Integer> size, String[] sort
    ) {
        return null;
    }

    @Override
    public ResponseEntity<FornecedorDTO> buscarPorId(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> criar(FornecedorDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<FornecedorDTO> atualizar(UUID id, FornecedorDTO dto) {
        return null;
    }

    @Override
    public ResponseEntity<Void> excluir(UUID id) {
        return null;
    }
}
