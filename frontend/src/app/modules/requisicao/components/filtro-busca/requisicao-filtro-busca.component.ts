import { UsuarioService } from "./../../../usuario/services/usuario.service";
import { Observable } from "rxjs";
import { Usuario } from "./../../../../model/usuario";
import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";

@Component({
    selector: "requisicao-filtro",
    templateUrl: "./requisicao-filtro-busca.component.html",
})
export class RequisicaoFiltroComponent {
    @Output("buscar") buscarEvent: EventEmitter<any> = new EventEmitter();

    @Input() almoxarifes: Usuario[];
    @Input() requisitantes: Usuario[];
    @Input() enums: any;

    filtro = {
        descricao: null,
        almoxarife: null,
        requisitante: null,
        status: null
    };

    constructor(private usuarioService: UsuarioService) {}

    onBuscar(): void {
        this.buscarEvent.emit({ filtro: this.filtro });
    }

    onLimpar(): void {
        this.filtro = {
            descricao: null,
            almoxarife: null,
            requisitante: null,
            status: null
        };
    }
}
