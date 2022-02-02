package com.almox.modules.common;

import com.almox.modules.auditavel.FiltroStatusAuditavel;
import com.almox.modules.auditavel.StatusAuditavel;
import com.almox.modules.movimento.model.TipoDeMovimento;
import com.almox.modules.movimento.model.TipoOrigemMovimento;
import com.almox.modules.orcamento.model.StatusOrcamento;
import com.almox.modules.pessoa.TipoEndereco;
import com.almox.modules.pessoa.TipoTelefone;
import com.almox.modules.produto.model.UnidadeMedida;
import com.almox.modules.requisicao.model.AcaoHistoricoItemRequisicao;
import com.almox.modules.requisicao.model.StatusItemRequisicao;
import com.almox.modules.requisicao.model.StatusRequisicao;
import com.almox.modules.usuario.model.TipoUsuario;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
        enums.put("tiposUsuarios", Lists.newArrayList(TipoUsuario.values()));
        enums.put("acoesHistoricoItemRequisicoes", Lists.newArrayList(AcaoHistoricoItemRequisicao.values()));
        enums.put("filtroStatusAuditavel", Lists.newArrayList(FiltroStatusAuditavel.values()));
        enums.put("statusAuditavel", Lists.newArrayList(StatusAuditavel.values()));
        enums.put("statusItemRequisicao", Lists.newArrayList(StatusItemRequisicao.values()));
        enums.put("statusOrcamento", Lists.newArrayList(StatusOrcamento.values()));
        enums.put("statusRequisicao", Lists.newArrayList(StatusRequisicao.values()));
        enums.put("tiposDeMovimento", Lists.newArrayList(TipoDeMovimento.values()));
        enums.put("tiposEndereco", Lists.newArrayList(TipoEndereco.values()));
        enums.put("tiposOrigemMovimento", Lists.newArrayList(TipoOrigemMovimento.values()));
        enums.put("tiposTelefone", Lists.newArrayList(TipoTelefone.values()));
        enums.put("unidadesMedida", Lists.newArrayList(UnidadeMedida.values()));

        return enums;
    }
}
