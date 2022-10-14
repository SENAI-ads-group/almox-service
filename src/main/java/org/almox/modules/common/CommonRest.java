package org.almox.modules.common;

import org.almox.modules.auditoria.StatusAuditavel;
import org.almox.modules.movimento.model.TipoDeMovimento;
import org.almox.modules.movimento.model.TipoOrigemMovimento;
import org.almox.modules.orcamento.StatusOrcamento;
import org.almox.modules.pedido.model.StatusPedido;
import org.almox.modules.produto.model.UnidadeMedida;
import org.almox.modules.requisicao.model.StatusRequisicao;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/common", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonRest {

    @GetMapping("/enumeradores")
    public Map<String, List<Enum<?>>> getEnumeradores() {
        Map<String, List<Enum<?>>> enums = new HashMap<>();
        enums.put("statusAuditavel", List.of(StatusAuditavel.values()));
        enums.put("statusOrcamento", List.of(StatusOrcamento.values()));
        enums.put("statusRequisicao", List.of(StatusRequisicao.values()));
        enums.put("statusPedido", List.of(StatusPedido.values()));
        enums.put("tiposDeMovimento", List.of(TipoDeMovimento.values()));
        enums.put("tiposOrigemMovimento", List.of(TipoOrigemMovimento.values()));
        enums.put("unidadesMedida", List.of(UnidadeMedida.values()));
        enums.put("statusRequisicoes", List.of(StatusRequisicao.values()));
        return enums;
    }
}
