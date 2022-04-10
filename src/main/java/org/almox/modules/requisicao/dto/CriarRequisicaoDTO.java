package org.almox.modules.requisicao.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

public class CriarRequisicaoDTO {
    public UUID idOperadorRequisitante;
    public UUID idOperadorAlmoxarife;
    public UUID idDepartamento;
    public Set<Item> itens;

    public static class Item {
        public UUID idProduto;
        public BigDecimal quantidade;
    }
}
