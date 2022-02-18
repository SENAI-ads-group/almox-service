import { Component, EventEmitter, Output } from '@angular/core';
import { FiltroConsideracaoAtivos } from 'src/model/enums';

@Component({
    selector: "departamento-filtro",
    templateUrl: "./departamento-filtro-busca.component.html",
})
export class DepartamentoFiltroComponent {
    @Output("buscar")
    buscarEvent: EventEmitter<any> = new EventEmitter();

    filtro = {
        nome: null,
        filtroStatusAuditavel: FiltroConsideracaoAtivos.APENAS_ATIVOS,
    };

    constructor() {}

    buscar(): void {
        this.buscarEvent.emit({ filtro: this.filtro });
    }

    limpar(): void {
        this.filtro = {
            nome: null,
            filtroStatusAuditavel: FiltroConsideracaoAtivos.APENAS_ATIVOS,
        };
    }
}
