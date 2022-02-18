import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";

import { PrimengModule } from "../primeng/primeng.module";
import { ProdutoModule } from "../produto/produto.module";
import { SharedModule } from "../shared/shared.module";
import { ItemPedidoFormComponent } from "./components/form/item-pedido-form.component";
import { ItemPedidoListaComponent } from "./components/lista/item-pedido-lista.component";

@NgModule({
    declarations: [ItemPedidoFormComponent, ItemPedidoListaComponent],
    imports: [CommonModule, SharedModule, PrimengModule, ProdutoModule],
    exports: [ItemPedidoFormComponent, ItemPedidoListaComponent],
})
export class ItemPedidoModule {}
