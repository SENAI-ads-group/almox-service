package org.almox.modules.produto;

import org.almox.modules.produto.model.ConfiguracaoEstoqueProduto;
import org.almox.modules.produto.repository.ConfiguracaoEstoqueProdutoRepository;
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
        if (configuracaoEstoqueProduto.isNew()) {
            configuracaoEstoqueProduto.setEstoqueAtual(BigDecimal.ZERO);
        }
        return repository.save(configuracaoEstoqueProduto);
    }
}
