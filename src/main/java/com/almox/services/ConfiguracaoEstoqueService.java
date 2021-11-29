package com.almox.services;

import com.almox.model.entidades.ConfiguracaoEstoqueProduto;
import com.almox.repositories.produto.ConfiguracaoEstoqueProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ConfiguracaoEstoqueService {

    private final ConfiguracaoEstoqueProdutoRepository repository;

    @Autowired
    public ConfiguracaoEstoqueService(ConfiguracaoEstoqueProdutoRepository repository) {
        this.repository = repository;
    }

    public ConfiguracaoEstoqueProduto salvar(ConfiguracaoEstoqueProduto configuracaoEstoqueProduto) {
        if(!configuracaoEstoqueProduto.estaPersistida()) {
            configuracaoEstoqueProduto.setEstoqueAtual(BigDecimal.ZERO);
        }
        return repository.save(configuracaoEstoqueProduto);
    }
}
