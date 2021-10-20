package com.almox.model.dto;

import com.almox.model.entidades.EntidadePadrao;
import com.almox.model.entidades.ItemMovimento;
import com.almox.model.entidades.Movimento;
import com.almox.model.entidades.Produto;
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
public class ItemMovimentoDTO extends AuditavelDTO<ItemMovimento,ItemMovimentoDTO>{

    private Long id;
    private Produto produto;
    private BigDecimal quantidade;
    private BigDecimal valorItem;
    private Movimento movimento;

public ItemMovimentoDTO(ItemMovimento itemMovimento){
    super(itemMovimento);
    id = itemMovimento.getId();
    produto = itemMovimento.getProduto();
    quantidade = itemMovimento.getQuantidade();
    valorItem = itemMovimento.getValorItem();
    movimento = itemMovimento.getMovimento();
}


    @Override
    public List<ItemMovimentoDTO> entidadeListParaDTOList(Collection<ItemMovimento> itemMovimentoList) {
        return itemMovimentoList.stream()
                .map(ItemMovimentoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public ItemMovimentoDTO entidadeParaDTO(ItemMovimento entidade) {
        return new ItemMovimentoDTO(entidade);
    }

    @Override
    public ItemMovimento dtoParaEntidade(ItemMovimentoDTO itemMovimentoDTO) {
        return new ItemMovimento(itemMovimentoDTO.getId(), itemMovimentoDTO.getProduto(), itemMovimentoDTO.getQuantidade(),
                itemMovimentoDTO.getValorItem(), itemMovimentoDTO.getMovimento());
    }
}
