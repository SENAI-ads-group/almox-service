package org.almox.modules.pessoa.dto.mapper;

import org.almox.modules.pessoa.dto.PessoaJuridicaDTO;
import org.almox.modules.pessoa.model.PessoaJuridica;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PessoaJuridicaMapper {

    PessoaJuridicaMapper INSTANCE = Mappers.getMapper(PessoaJuridicaMapper.class);

    PessoaJuridicaDTO toDTO(PessoaJuridica pessoaJuridica);

    PessoaJuridica fromDTO(PessoaJuridicaDTO dto);

    List<PessoaJuridica> fromDTOList(List<PessoaJuridicaDTO> dtoList);

    List<PessoaJuridicaDTO> toDTOList(List<PessoaJuridica> dtoList);
}
