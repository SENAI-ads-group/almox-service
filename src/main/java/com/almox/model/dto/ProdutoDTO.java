package com.almox.model.dto;

import com.almox.model.entidades.*;
import com.almox.model.enums.UnidadeMedida;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ProdutoDTO extends AuditavelDTO<Produto, ProdutoDTO>{

    public static final ProdutoDTO INSTANCIA = new ProdutoDTO();

    private Long id;
    private String descricao;
    private BigDecimal custoMedio;
    private String codigoBarras;
    private Boolean possuiLoteValidade;
    //private Set<Fornecedor> fornecedores = new HashSet<>();
    private UnidadeMedida unidadeMedida;
    private PalavraChave palavrasChaves;
    private Fabricante fabricante;
    private String detalhe;
    private Set<Departamento> departamentos = new HashSet<>();
    private Grupo grupo;

public ProdutoDTO(Produto pdt){
    super(pdt);
    id = pdt.getId();
    descricao = pdt.getDescricao();
    custoMedio = pdt.getCustoMedio();
    codigoBarras = pdt.getCodigoBarras();
    possuiLoteValidade = pdt.getPossuiLoteValidade();
    unidadeMedida = pdt.getUnidadeMedida();
    palavrasChaves = pdt.getPalavraChave();
    fabricante = pdt.getFabricante();
    detalhe = pdt.getDetalhe();
    departamentos = pdt.getDepartamentos();
    grupo = pdt.getGrupo();
}

    @Override
    public ProdutoDTO entidadeParaDTO(Produto entidade) {
        return new ProdutoDTO(entidade);
    }

    @Override
    public Produto dtoParaEntidade(ProdutoDTO produtoDTO) {
        return new Produto(produtoDTO.getId(), produtoDTO.getDescricao(), produtoDTO.getCustoMedio(), produtoDTO.getCodigoBarras(), produtoDTO.getPossuiLoteValidade(),
                produtoDTO.getUnidadeMedida(),produtoDTO.getFabricante(),produtoDTO.getDetalhe(),produtoDTO.getPalavrasChaves(),
                produtoDTO.getDepartamentos(),produtoDTO.getGrupo());
    }

    @Override
    public List<ProdutoDTO> entidadeListParaDTOList(Collection<Produto> produtoList) {
        return produtoList.stream()
                .map(ProdutoDTO::new)
                .collect(Collectors.toList());
    }
}
