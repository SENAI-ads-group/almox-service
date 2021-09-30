package com.almox.model.dto;

import com.almox.model.entidades.Fabricante;

import java.util.List;
import java.util.stream.Collectors;

public class FabricanteDTO extends PessoaJuridicaDTO<Fabricante, FabricanteDTO> {

    private Long id;

    public FabricanteDTO(Fabricante fab) {
        super(fab);
        id = fab.getId();
    }

    @Override
    public FabricanteDTO entidadeParaDTO(Fabricante entidade) {
        return new FabricanteDTO(entidade);
    }

    @Override
    public Fabricante dtoParaEntidade(FabricanteDTO fabricanteDTO) {
        return new Fabricante(id, fabricanteDTO.getRazaoSocial(), fabricanteDTO.getCnpj(), fabricanteDTO.getNomeFantasia(),
                ContatoDTO.INSTANCIA.dtoParaEntidade(fabricanteDTO.getContato()));
    }

    @Override
    public List<FabricanteDTO> entidadeListParaDTOList(List<Fabricante> fabricanteList) {
        return fabricanteList.stream()
                .map(FabricanteDTO::new)
                .collect(Collectors.toList());
    }
}
