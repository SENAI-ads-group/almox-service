import { Component, EventEmitter, Input, Output } from "@angular/core";

import { ItemPedido } from "../../../../model/item-pedido";

@Component({
    selector: "item-pedido-lista",
    templateUrl: "./item-pedido-lista.component.html",
    styleUrls: ["./item-pedido-lista.component.scss"],
})
export class ItemPedidoListaComponent {
    @Input() itens: ItemPedido[];
    @Output() editar = new EventEmitter<any>();
    @Output() excluir = new EventEmitter<any>();

    constructor() {}

    handleEditar(rowIndex: number, item: ItemPedido) {
        this.editar.emit({ rowIndex, item });
    }

    handleExcluir(rowIndex: number) {
        this.excluir.emit({ rowIndex });
    }
}
