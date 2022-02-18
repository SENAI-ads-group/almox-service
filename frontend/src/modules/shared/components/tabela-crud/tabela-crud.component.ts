import { Component, ContentChild, EventEmitter, Input, OnInit, Output, TemplateRef } from '@angular/core';
import * as moment from 'moment';
import { Auditavel } from 'src/model/auditavel';
import { StatusEntidadeAuditavel } from 'src/model/enums';
import * as NumberUtil from 'src/utils/NumberUtil';
import * as StringUtil from 'src/utils/StringUtil';

import { Coluna, TipoColuna } from './coluna';

@Component({
    selector: "tabela-crud",
    templateUrl: "./tabela-crud.component.html",
    styleUrls: ["./tabela-crud-component.scss"],
})
export class TabelaCrudComponent<T> implements OnInit {
    @Input("dados") dados: T[];
    @Input("selecionados") selecionados: T[];
    @Input("efeitoLinhaSelecionada") efeitoLinhaSelecionada = false;
    @Input("paginacao") paginacao = true;
    @Input("quantidadeLinhas") quantidadeLinhas = 10;
    @Input("colunas") colunas: any[];
    @Input("exibirAcaoVisualizar") exibirAcaoVisualizar = (t: T) => true;
    @Input("exibirAcaoEditar") exibirAcaoEditar = (t: T) => true;
    @Input("exibirAcaoExcluir") exibirAcaoExcluir = (t: T) => true;
    @Input("loading") loading : boolean;

    @Output("selecionadosChange") selecionadosEvent = new EventEmitter<T[]>();
    @Output("visualizar") visualizarEvent = new EventEmitter<T>();
    @Output("editar") editarEvent = new EventEmitter<T>();
    @Output("excluir") excluirEvent = new EventEmitter<T>();

    @ContentChild(TemplateRef) botoesAcaoTemplateRef: TemplateRef<any>;

    constructor() {}

    ngOnInit(): void {}

    selecionadosChange() {
        this.selecionadosEvent.emit(this.selecionados);
    }

    handleOnVisualizar(dado: T) {
        this.visualizarEvent.emit(dado);
    }

    handleOnEditar(dado: T) {
        this.editarEvent.emit(dado);
    }

    handleOnExcluir(dado: T) {
        this.excluirEvent.emit(dado);
    }

    renderizarConteudo(dadoLinha: T, coluna: Coluna) {
        const propriedades: string[] = coluna.campo.split(".");
        let propriedadeRenderizada = dadoLinha[propriedades[0]];
        if (!propriedadeRenderizada) return propriedadeRenderizada;

        for (let i = 1; i < propriedades.length; i++) {
            const subpropriedade = propriedadeRenderizada[propriedades[i]];
            propriedadeRenderizada = subpropriedade;
        }
        return {
            styleClass: this.resolverStyleCssColuna(dadoLinha, coluna),
            conteudo: this.formatarConteudoColuna(
                propriedadeRenderizada,
                coluna
            ),
        };
    }

    formatarConteudoColuna(conteudo: string | number, coluna: Coluna): string | number {
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

    resolverStyleCssColuna(dadoLinha: T, coluna: Coluna) {
        if (coluna.tipo === TipoColuna.STATUS_AUDITAVEL) {
            if (!("statusAuditoria" in dadoLinha)) return null;
            const auditavel = <Auditavel>dadoLinha;
            const { classBadge } =
                StatusEntidadeAuditavel[
                    (auditavel.statusAuditoria || {}).type
                ] || {};
            return `auditavel-badge status-${classBadge}`;
        }
        return null;
    }
}
