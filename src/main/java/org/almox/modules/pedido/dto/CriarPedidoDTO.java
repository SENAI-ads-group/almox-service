package org.almox.modules.pedido.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public class CriarPedidoDTO {
    public UUID idFornecedor;
    public LocalDateTime dataPrevisaoEntrega;
    public Set<Item> itens;

    public static class Item {
        public UUID idProduto;
        public BigDecimal quantidade;
    }
}
