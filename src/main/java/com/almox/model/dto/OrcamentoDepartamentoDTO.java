package com.almox.model.dto;

import com.almox.model.entidades.ItemOrcamentoDepartamento;
import com.almox.model.entidades.OrcamentoDepartamento;
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
public class OrcamentoDepartamentoDTO extends EntidadeDTO<OrcamentoDepartamento, OrcamentoDepartamentoDTO> {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private BigDecimal valorLimite;
    private StatusOrcamento status;
    private List<ItemOrcamentoDepartamentoDTO> itensPertencentes;

    public OrcamentoDepartamentoDTO(OrcamentoDepartamento orcamentoDepartamento) {
        id = orcamentoDepartamento.getId();
        dataInicio = orcamentoDepartamento.getDataInicio();
        dataTermino = orcamentoDepartamento.getDataTermino();
        valorLimite = orcamentoDepartamento.getValorLimite();
        status = orcamentoDepartamento.getStatus();
        itensPertencentes = orcamentoDepartamento.getItensPertencentes().
                stream()
                .map(ItemOrcamentoDepartamentoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public OrcamentoDepartamentoDTO entidadeParaDTO(OrcamentoDepartamento entidade) {
        return new OrcamentoDepartamentoDTO(entidade);
    }

    @Override
    public OrcamentoDepartamento dtoParaEntidade(OrcamentoDepartamentoDTO orcamentoDepartamentoDTO) {
        OrcamentoDepartamento entidade = new OrcamentoDepartamento();
        entidade.setId(id);
        entidade.setDataInicio(dataInicio);
        entidade.setDataTermino(dataTermino);
        entidade.setValorLimite(valorLimite);
        entidade.setStatus(status);
        return entidade;
    }

    @Override
    public List<OrcamentoDepartamentoDTO> entidadeListParaDTOList(Collection<OrcamentoDepartamento> entidadeList) {
        return entidadeList.stream()
                .map(OrcamentoDepartamentoDTO::new)
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemOrcamentoDepartamentoDTO {
        private LocalDateTime dataLancamento;
        private BigDecimal valor;

        public ItemOrcamentoDepartamentoDTO(ItemOrcamentoDepartamento itemOrcamentoDepartamento) {
            dataLancamento = itemOrcamentoDepartamento.getDataLancamento();
            valor = itemOrcamentoDepartamento.getValor();
        }
    }
}
