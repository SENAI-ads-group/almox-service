import { Component, EventEmitter, Input, Output } from "@angular/core";

import { ItemRequisicao } from "./../../../../model/item-requisicao";

@Component({
    selector: "item-requisicao-lista",
    templateUrl: "./lista.component.html",
    styleUrls: ["./lista.component.scss"],
})
export class ItemRequisicaoListaComponent {
    @Input() itens: ItemRequisicao[];
    @Output() editar = new EventEmitter<any>();

    constructor() {}

    onEditar(rowIndex: number, item: ItemRequisicao) {
        this.editar.emit({ rowIndex, item });
    }
}
