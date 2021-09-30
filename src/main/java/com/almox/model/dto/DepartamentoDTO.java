package com.almox.model.dto;


import com.almox.model.entidades.Departamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class DepartamentoDTO extends AuditavelDTO<Departamento, DepartamentoDTO> {

    private Long id;
    private String nome;
    private List<UsuarioDTO> usuarios = new ArrayList<>();

    public DepartamentoDTO(Departamento dpto) {
        super(dpto);
        id = dpto.getId();
        nome = dpto.getNome();
        usuarios = UsuarioDTO.INSTANCIA.entidadeListParaDTOList(dpto.getUsuarios());
    }

    @Override
    public DepartamentoDTO entidadeParaDTO(Departamento entidade) {
        return new DepartamentoDTO(entidade);
    }

    @Override
    public Departamento dtoParaEntidade(DepartamentoDTO departamentoDTO) {
        return new Departamento(id, nome, null);
    }

    @Override
    public List<DepartamentoDTO> entidadeListParaDTOList(Collection<Departamento> departamentoList) {
        return departamentoList.stream()
                .map(DepartamentoDTO::new)
                .collect(Collectors.toList());
    }
}
