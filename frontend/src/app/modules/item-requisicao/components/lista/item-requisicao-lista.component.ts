import { Component, EventEmitter, Input, Output } from "@angular/core";

import { ItemRequisicao } from "../../../../model/item-requisicao";

@Component({
    selector: "item-requisicao-lista",
    templateUrl: "./item-requisicao-lista.component.html",
    styleUrls: ["./item-requisicao-lista.component.scss"],
})
export class ItemRequisicaoListaComponent {
    @Input() itens: ItemRequisicao[];
    @Output() editar = new EventEmitter<any>();
    @Output() excluir = new EventEmitter<any>();

    constructor() {}

    handleEditar(rowIndex: number, item: ItemRequisicao) {
        this.editar.emit({ rowIndex, item });
    }

    handleExcluir(rowIndex: number) {
        this.excluir.emit({ rowIndex });
    }
}
