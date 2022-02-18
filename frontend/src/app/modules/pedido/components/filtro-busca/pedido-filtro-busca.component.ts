import { UsuarioService } from "../../../usuario/services/usuario.service";
import { Observable } from "rxjs";
import { Usuario } from "../../../../model/usuario";
import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";

@Component({
    selector: "pedido-filtro",
    templateUrl: "./pedido-filtro-busca.component.html",
})
export class PedidoFiltroComponent {
    @Output("buscar") buscarEvent: EventEmitter<any> = new EventEmitter();

    @Input() compradores: Usuario[];
    @Input() fornecedores: Usuario[];
    @Input() enums: any;

    filtro = {
        descricao: null,
        comprador: null,
        fornecedor: null
    };

    constructor(private usuarioService: UsuarioService) {}

    onBuscar(): void {
        this.buscarEvent.emit({ filtro: this.filtro });
    }

    onLimpar(): void {
        this.filtro = {
            descricao: null,
            comprador: null,
            fornecedor: null,
        };
    }
}
