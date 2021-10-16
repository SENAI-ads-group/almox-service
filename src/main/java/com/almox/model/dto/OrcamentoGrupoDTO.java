package com.almox.model.dto;

import com.almox.model.entidades.ItemOrcamentoGrupo;
import com.almox.model.entidades.OrcamentoGrupo;
import com.almox.model.enums.StatusOrcamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrcamentoGrupoDTO extends EntidadeDTO<OrcamentoGrupo, OrcamentoGrupoDTO> {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private BigDecimal valorLimite;
    private StatusOrcamento status;
    private List<ItemOrcamentoGrupoDTO> itensPertencentes;

public OrcamentoGrupoDTO(OrcamentoGrupo orcamentoGrupo){
    id = orcamentoGrupo.getId();
    dataInicio = orcamentoGrupo.getDataInicio();
    dataTermino = orcamentoGrupo.getDataTermino();
    valorLimite = orcamentoGrupo.getValorLimite();
    status = orcamentoGrupo.getStatus();
    itensPertencentes = orcamentoGrupo.getItensPertencentes().
            stream()
            .map(OrcamentoGrupoDTO.ItemOrcamentoGrupoDTO::new)
            .collect(Collectors.toList());
}

    @Override
    public OrcamentoGrupoDTO entidadeParaDTO(OrcamentoGrupo entidade) {
        return new OrcamentoGrupoDTO(entidade);
    }

    @Override
    public OrcamentoGrupo dtoParaEntidade(OrcamentoGrupoDTO orcamentoGrupoDTO) {
    OrcamentoGrupo entidade = new OrcamentoGrupo();
        entidade.setId(id);
        entidade.setDataInicio(dataInicio);
        entidade.setDataTermino(dataTermino);
        entidade.setValorLimite(valorLimite);
        entidade.setStatus(status);
        return entidade;
    }

    @Override
    public List<OrcamentoGrupoDTO> entidadeListParaDTOList(Collection<OrcamentoGrupo> entidadeList) {
        return entidadeList.stream()
                .map(OrcamentoGrupoDTO::new)
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemOrcamentoGrupoDTO{
    private LocalDateTime dataLancamento;
    private BigDecimal valor;

    public ItemOrcamentoGrupoDTO(ItemOrcamentoGrupo itemOrcamentoGrupo){
        dataLancamento = itemOrcamentoGrupo.getDataLancamento();
        valor = itemOrcamentoGrupo.getValor();
    }

    }




}
