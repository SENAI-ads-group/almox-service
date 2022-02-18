import { Component, EventEmitter, Input, Output } from "@angular/core";

import { FiltroConsideracaoAtivos } from "../../../../model/enums";
import { Departamento } from "../../../../model/departamento";
import { Grupo } from "../../../../model/grupo";

@Component({
    selector: "produto-filtro",
    templateUrl: "./produto-filtro-busca.component.html",
})
export class ProdutoFiltroComponent {
    @Output("buscar") buscarEvent: EventEmitter<any> = new EventEmitter();
    @Input() grupos: Grupo[];
    @Input() departamentos: Departamento[];

    filtro = {
        descricao: null,
        codigoBarras: null,
        grupos: null,
        fornecedor: null,
        departamentos: null,
        unidadeMedida: null,
        filtroStatusAuditavel: FiltroConsideracaoAtivos.APENAS_ATIVOS,
    };

    constructor() {}

    buscar(): void {
        this.buscarEvent.emit({ filtro: this.filtro });
    }

    limpar(): void {
        this.filtro = {
            descricao: null,
            codigoBarras: null,
            grupos: null,
            fornecedor: null,
            departamentos: null,
            unidadeMedida: null,
            filtroStatusAuditavel: FiltroConsideracaoAtivos.APENAS_ATIVOS,
        };
    }
}
