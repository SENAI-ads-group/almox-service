package com.almox.model.dto;

import com.almox.model.entidades.Grupo;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO extends AuditavelDTO <Grupo, GrupoDTO>{

    private long id;
    private String descricao;

    public GrupoDTO(Grupo grupo) {
        super(grupo);
        id = grupo.getId();
        descricao = grupo.getDescricao();
    }

    @Override
    public GrupoDTO entidadeParaDTO(Grupo entidade) {
        return new GrupoDTO(entidade);
    }

    @Override
    public Grupo dtoParaEntidade(GrupoDTO grupoDTO) {
        return new Grupo(id,descricao);
    }

    @Override
    public List<GrupoDTO> entidadeListParaDTOList(List<Grupo>grupoList) {
        return grupoList.stream()
                .map(GrupoDTO::new)
                .collect(Collectors.toList());
    }
}

