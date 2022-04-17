package org.almox.modules.auditoria;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.almox.modules.common.IEnum;

import javax.validation.constraints.NotNull;

public class FiltroStatusAuditoria {
    @NotNull
    public Tipo statusAuditoria;

    public FiltroStatusAuditoria(Tipo statusAuditoria) {
        this.statusAuditoria = statusAuditoria;
    }

    @Getter
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum Tipo implements IEnum {
        CONSIDERAR_TODOS("Considerar Todos"),
        APENAS_ATIVOS("Considerar Apenas Ativos"),
        APENAS_EXCLUIDOS("Considerar Apenas Excluídos");

        private String descricao;

        @JsonCreator
        public static Tipo deserialize(@JsonProperty("type") String type) {
            return IEnum.fromType(values(), type);
        }

        @Override
        public String getType() {
            return name();
        }
    }
}
