import {
    Component,
    ContentChild,
    EventEmitter,
    Input,
    OnInit,
    Output,
    TemplateRef,
} from "@angular/core";
import * as moment from "moment";
import { Auditavel } from "src/app/model/auditavel";
import { StatusEntidadeAuditavel } from "src/app/model/enums";
import * as NumberUtil from "src/app/utils/NumberUtil";
import * as StringUtil from "src/app/utils/StringUtil";

import { Coluna, TipoColuna } from "../tabela-crud/coluna";

@Component({
    selector: "tabela",
    templateUrl: "./tabela.component.html",
    styleUrls: ["./tabela.component.scss"],
})
export class TabelaComponent<T> implements OnInit {
    @Input("dados") dados: T[];
    @Input("selecionados") selecionados: T[];
    @Input("efeitoLinhaSelecionada") efeitoLinhaSelecionada = false;
    @Input("paginacao") paginacao = true;
    @Input("quantidadeLinhas") quantidadeLinhas = 10;
    @Input("colunas") colunas: any[];
    @Input("loading") loading: boolean;

    @Output("selecionadosChange") selecionadosEvent = new EventEmitter<T[]>();

    @ContentChild(TemplateRef) botoesAcaoTemplateRef: TemplateRef<any>;

    constructor() {}

    ngOnInit(): void {}

    selecionadosChange() {
        this.selecionadosEvent.emit(this.selecionados);
    }

    renderizarConteudo(dadoLinha: T, coluna: Coluna) {
        const propriedades: string[] = coluna.campo.split(".");
        let propriedadeRenderizada = dadoLinha[propriedades[0]];
        if (!propriedadeRenderizada) return propriedadeRenderizada;

        for (let i = 1; i < propriedades.length; i++) {
            const subpropriedade = propriedadeRenderizada[propriedades[i]];
            propriedadeRenderizada = subpropriedade;
        }
        return this.formatarConteudoColuna(propriedadeRenderizada, coluna);
    }

    formatarConteudoColuna(
        conteudo: string | number,
        coluna: Coluna
    ): string | number {
        switch (coluna.tipo) {
            case TipoColuna.DATA_HORA: {
                return moment.utc(conteudo).isValid()
                    ? moment.utc(conteudo).format("DD/MM/YYYY HH:mm:ss")
                    : "";
            }
            case TipoColuna.DATA: {
                return moment.utc(conteudo).isValid()
                    ? moment.utc(conteudo).format("DD/MM/YYYY")
                    : "";
            }
            case TipoColuna.PERCENTUAL: {
                return NumberUtil.numberToStringPercentual(Number(conteudo));
            }
            case TipoColuna.MONETARIO: {
                return NumberUtil.numberToStringMonetario(Number(conteudo));
            }
            case TipoColuna.DECIMAL: {
                return NumberUtil.numberToStringDecimal(Number(conteudo));
            }
            case TipoColuna.INTEIRO: {
                return NumberUtil.numberToStringInteiro(Number(conteudo));
            }
            case TipoColuna.BOOLEANO: {
                return conteudo ? "Sim" : "Não";
            }
            case TipoColuna.CPF_CNPJ: {
                return StringUtil.aplicarMascaraCpfCNPJ(String(conteudo));
            }
            default: {
                return conteudo;
            }
        }
    }
}
