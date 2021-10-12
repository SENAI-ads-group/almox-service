package com.almox.model.dto;

import com.almox.model.entidades.ConfiguracaoEstoqueProduto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ConfiguracaoEstoqueProdutoDTO extends EntidadeDTO<ConfiguracaoEstoqueProduto, ConfiguracaoEstoqueProdutoDTO> {

    private Long id;
    private BigDecimal estoqueMinimo;
    private BigDecimal estoqueAtual;
    private BigDecimal estoqueMaximo;
    private boolean controlaEstoqueMinimo;
    private boolean controlaEstoqueMaximo;

    public ConfiguracaoEstoqueProdutoDTO(ConfiguracaoEstoqueProduto entidade) {
        id = entidade.getId();
        estoqueMinimo = entidade.getEstoqueMinimo();
        estoqueAtual = entidade.getEstoqueAtual();
        estoqueMaximo = entidade.getEstoqueMaximo();
        controlaEstoqueMinimo = entidade.getControlaEstoqueMinimo();
        controlaEstoqueMaximo = entidade.getControlaEstoqueMaximo();
    }

    @Override
    public ConfiguracaoEstoqueProdutoDTO entidadeParaDTO(ConfiguracaoEstoqueProduto entidade) {
        return new ConfiguracaoEstoqueProdutoDTO(entidade);
    }

    @Override
    public ConfiguracaoEstoqueProduto dtoParaEntidade(ConfiguracaoEstoqueProdutoDTO configuracaoEstoqueProdutoDTO) {
        return new ConfiguracaoEstoqueProduto(id, estoqueMinimo, estoqueAtual, estoqueMaximo, controlaEstoqueMinimo, controlaEstoqueMaximo);
    }

    @Override
    public List<ConfiguracaoEstoqueProdutoDTO> entidadeListParaDTOList(Collection<ConfiguracaoEstoqueProduto> entidadeList) {
        return entidadeList.stream()
                .map(ConfiguracaoEstoqueProdutoDTO::new)
                .collect(Collectors.toList());
    }
}
