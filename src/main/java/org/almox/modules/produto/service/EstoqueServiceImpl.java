package org.almox.modules.produto.service;

import lombok.RequiredArgsConstructor;
import org.almox.modules.produto.model.Estoque;
import org.almox.modules.produto.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EstoqueServiceImpl implements EstoqueService {

    private final EstoqueRepository repository;

    public Estoque salvar(Estoque estoque) {
        if (estoque.isNew()) estoque.setEstoqueAtual(BigDecimal.ZERO);
        return repository.save(estoque);
    }
}
