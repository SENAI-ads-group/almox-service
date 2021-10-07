package com.almox.model.dto;

import com.almox.model.entidades.EntidadePadrao;

import java.util.Collection;
import java.util.List;

public abstract class EntidadeDTO<E extends EntidadePadrao, DTO> {

    public abstract DTO entidadeParaDTO(E entidade);

    public abstract E dtoParaEntidade(DTO dto);

    public abstract List<DTO> entidadeListParaDTOList(Collection<E> entidadeList);
}
