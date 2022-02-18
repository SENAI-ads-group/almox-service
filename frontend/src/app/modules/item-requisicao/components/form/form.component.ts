import { Component, EventEmitter, Input, Output } from "@angular/core";
import { DialogService, DynamicDialogRef } from "primeng/dynamicdialog";
import { ProdutoModalListaComponent } from "src/app/modules/produto/components/produto-modal-lista/produto-modal-lista.component";

import { ItemRequisicao } from "./../../../../model/item-requisicao";

@Component({
    selector: "item-requisicao-form",
    templateUrl: "./form.component.html",
    styleUrls: ["./form.component.scss"],
})
export class ItemRequisicaoFormComponent {
    @Input() item: ItemRequisicao = { produto: {} };
    @Output() submit = new EventEmitter<ItemRequisicao>();

    constructor(private dialogService: DialogService) {}

    dialogRefProduto: DynamicDialogRef;

    abrirDialogProduto() {
        this.dialogRefProduto = this.dialogService.open(
            ProdutoModalListaComponent,
            {
                header: "Escolha um Produto",
                width: "70%",
                contentStyle: { "max-height": "500px", overflow: "auto" },
                baseZIndex: 10000,
            }
        );

        this.dialogRefProduto.onClose.subscribe(produtoSelecionado => {
            if (!produtoSelecionado) return;
            this.item.produto = produtoSelecionado;
        });
    }
}
