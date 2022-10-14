package org.almox.modules.pessoa.dto;

import org.almox.modules.pessoa.model.Pessoa;
import org.almox.modules.pessoa.model.PessoaFisica;
import org.almox.modules.pessoa.model.PessoaJuridica;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class PessoaMapper {

    @Autowired
    protected PessoaJuridicaMapper pessoaJuridicaMapper;
    @Autowired
    protected PessoaFisicaMapper pessoaFisicaMapper;

    public PessoaDTO toDTO(Pessoa pessoa) {
        if (pessoa instanceof PessoaFisica)
            return pessoaFisicaMapper.toDTO((PessoaFisica) pessoa);
        else if (pessoa instanceof PessoaJuridica)
            return pessoaJuridicaMapper.toDTO((PessoaJuridica) pessoa);
        else
            return null;
    }

    public Pessoa fromDTO(PessoaDTO dto) {
        if (dto instanceof PessoaFisicaDTO)
            return pessoaFisicaMapper.fromDTO((PessoaFisicaDTO) dto);
        else if (dto instanceof PessoaJuridicaDTO)
            return pessoaJuridicaMapper.fromDTO((PessoaJuridicaDTO) dto);
        else
            return null;
    }

    public List<PessoaDTO> toDTOList(List<Pessoa> pessoas) {
        if (pessoas == null)
            return null;
        return pessoas.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
