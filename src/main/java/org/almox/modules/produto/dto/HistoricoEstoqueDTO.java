package org.almox.modules.produto.dto;

import org.almox.modules.common.DTOSimples;
import org.almox.modules.movimento.model.TipoDeMovimento;
import org.almox.modules.movimento.model.TipoOrigemMovimento;
import org.almox.modules.operador.dto.OperadorDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class HistoricoEstoqueDTO {
    public UUID id;
    public LocalDateTime dataRegistro;
    public BigDecimal estoqueAnterior;
    public BigDecimal estoqueFinal;
    public ItemMovimentoHistorico itemMovimento;
    public MovimentoHistorico movimento;
    public ProdutoDTO produto;
    public OperadorDTO operador;

    public static class ItemMovimentoHistorico extends DTOSimples {
        public BigDecimal quantidade;
        public BigDecimal custoLiquido;
        public BigDecimal custoBruto;
    }

    public static class MovimentoHistorico extends DTOSimples {
        public LocalDate data;
        public BigDecimal custoLiquido;
        public BigDecimal custoBruto;
        public TipoDeMovimento tipoDeMovimento;
        public TipoOrigemMovimento tipoOrigemMovimento;
        public UUID idOrigem;
    }
}
