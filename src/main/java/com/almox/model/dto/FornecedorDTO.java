package com.almox.model.dto;


import com.almox.model.entidades.Fornecedor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class FornecedorDTO extends PessoaJuridicaDTO<Fornecedor, FornecedorDTO> {

    private Long id;

    public FornecedorDTO(Fornecedor forn) {
        super(forn);
        id = forn.getId();

    }

    @Override
    public FornecedorDTO entidadeParaDTO(Fornecedor entidade) {
        return new FornecedorDTO(entidade);
    }

    @Override
    public Fornecedor dtoParaEntidade(FornecedorDTO fornecedorDTO) {
        return new Fornecedor(id, fornecedorDTO.getRazaoSocial(), fornecedorDTO.getRazaoSocial(), fornecedorDTO.getNomeFantasia(),
                ContatoDTO.INSTANCIA.dtoParaEntidade(fornecedorDTO.getContato()));
    }

    @Override
    public List<FornecedorDTO> entidadeListParaDTOList(Collection<Fornecedor> fornecedorList) {
        return fornecedorList.stream()
                .map(FornecedorDTO::new)
                .collect(Collectors.toList());
    }

    //    @ManyToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    //    private Set<Produto>produtosFornecidos = new HashSet<>();
}