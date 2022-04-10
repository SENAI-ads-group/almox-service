package org.almox.modules.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.almox.modules.auditoria.FiltroStatusAuditavel;
import org.almox.modules.auditoria.StatusAuditavel;
import org.almox.modules.movimento.model.TipoDeMovimento;
import org.almox.modules.movimento.model.TipoOrigemMovimento;
import org.almox.modules.orcamento.StatusOrcamento;
import org.almox.modules.pedido.model.StatusPedido;
import org.almox.modules.produto.model.UnidadeMedida;
import org.almox.modules.requisicao.model.StatusRequisicao;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/common", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommonRest {

    @GetMapping("/enumeradores")
    public Map<String, List<Enum<?>>> getEnumeradores() {
        Map<String, List<Enum<?>>> enums = Maps.newHashMap();
        enums.put("filtroStatusAuditavel", Lists.newArrayList(FiltroStatusAuditavel.values()));
        enums.put("statusAuditavel", Lists.newArrayList(StatusAuditavel.values()));
        enums.put("statusOrcamento", Lists.newArrayList(StatusOrcamento.values()));
        enums.put("statusRequisicao", Lists.newArrayList(StatusRequisicao.values()));
        enums.put("statusPedido", Lists.newArrayList(StatusPedido.values()));
        enums.put("tiposDeMovimento", Lists.newArrayList(TipoDeMovimento.values()));
        enums.put("tiposOrigemMovimento", Lists.newArrayList(TipoOrigemMovimento.values()));
        enums.put("unidadesMedida", Lists.newArrayList(UnidadeMedida.values()));
        enums.put("statusRequisicoes", Lists.newArrayList(StatusRequisicao.values()));

        return enums;
    }
}
