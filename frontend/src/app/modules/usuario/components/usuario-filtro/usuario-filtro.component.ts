import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { FiltroConsideracaoAtivos } from "src/app/model/enums/filtro-consideracao-ativos";

@Component({
    selector: "usuario-filtro",
    templateUrl: "./usuario-filtro.component.html",
})
export class UsuarioFiltroComponent implements OnInit {
    @Output("buscar")
    buscarEvent: EventEmitter<any> = new EventEmitter();

    @Input()
    tiposUsuarios: any[];

    filtro = {
        nome: null,
        email: null,
        tipoUsuario: null,
        filtroStatusAuditavel: FiltroConsideracaoAtivos.APENAS_ATIVOS,
    };

    constructor() {}

    ngOnInit(): void {}

    buscar(): void {
        this.buscarEvent.emit({ filtro: this.filtro });
    }

    limparFiltros(): void {
        this.filtro = {
            nome: null,
            email: null,
            tipoUsuario: null,
            filtroStatusAuditavel: FiltroConsideracaoAtivos.APENAS_ATIVOS,
        };
    }
}
