package org.almox.modules.pessoa.dto;

import org.almox.modules.pessoa.dto.PessoaFisicaDTO;
import org.almox.modules.pessoa.model.PessoaFisica;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PessoaFisicaMapper {

    PessoaFisicaDTO toDTO(PessoaFisica pessoaFisica);

    PessoaFisica fromDTO(PessoaFisicaDTO dto);

    List<PessoaFisica> fromDTOList(List<PessoaFisicaDTO> dtoList);

    List<PessoaFisicaDTO> toDTOList(List<PessoaFisica> dtoList);
}
