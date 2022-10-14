package org.almox.modules.fornecedor.dto;

import org.almox.modules.fornecedor.model.Fornecedor;
import org.almox.modules.pessoa.dto.PessoaJuridicaDTO;
import org.almox.modules.pessoa.dto.PessoaJuridicaMapper;
import org.almox.modules.pessoa.model.PessoaJuridica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FornecedorMapper {

    @Mappings({
            @Mapping(target = "pessoa", expression = "java(pessoaToDTO(fornecedor))")
    })
    FornecedorDTO toDTO(Fornecedor fornecedor);

    @Mappings({
            @Mapping(target = "pessoa", expression = "java(pessoaFromDTO(dto))")
    })
    Fornecedor fromDTO(FornecedorDTO dto);

    @Mappings({
            @Mapping(target = "pessoa", expression = "java(toPessoaJuridica(dto))")
    })
    Fornecedor toFornecedor(CriarFornecedorDTO dto);

    List<Fornecedor> fromDTOList(List<FornecedorDTO> dtoList);

    List<FornecedorDTO> toDTOList(List<Fornecedor> dtoList);

    default PessoaJuridicaDTO pessoaToDTO(Fornecedor fornecedor) {
        return PessoaJuridicaMapper.INSTANCE.toDTO(fornecedor.getPessoa());
    }

    default PessoaJuridica pessoaFromDTO(FornecedorDTO dto) {
        return PessoaJuridicaMapper.INSTANCE.fromDTO(dto.pessoa);
    }

    default PessoaJuridica toPessoaJuridica(CriarFornecedorDTO dto) {
        return PessoaJuridicaMapper.INSTANCE.toPessoaJuridica(dto.pessoa);
    }
}
