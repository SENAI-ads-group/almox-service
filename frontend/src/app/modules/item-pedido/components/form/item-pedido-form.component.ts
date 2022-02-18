import { ControlContainer, NgForm } from "@angular/forms";
import { Component, EventEmitter, Input, OnInit, Output } from "@angular/core";
import { DialogService, DynamicDialogRef } from "primeng/dynamicdialog";

import { ItemPedido } from "../../../../model/item-pedido";
import { ProdutoModalListaComponent } from "src/app/modules/produto/components/modal-listagem/produto-modal-lista.component";

@Component({
    selector: "item-pedido-form",
    templateUrl: "./item-pedido-form.component.html",
    styleUrls: ["./form.component.scss"],
})
export class ItemPedidoFormComponent {
    @Input() item: ItemPedido = {};
    @Output() submit = new EventEmitter<ItemPedido>();

    constructor(private dialogService: DialogService) {}

    dialogRefProduto: DynamicDialogRef;

    abrirDialogProduto() {
        this.dialogRefProduto = this.dialogService.open(
            ProdutoModalListaComponent,
            {
                header: "Selecione um produto clicando duas vezes sobre o mesmo",
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

    onSubmit = () => {
        if (
            !this.item.produto ||
            !this.item.quantidade ||
            this.item.quantidade <= 0
        ) {
            return;
        }

        this.submit.emit(this.item);
    };
}
