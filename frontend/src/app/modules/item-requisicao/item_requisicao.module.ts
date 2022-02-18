import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";

import { PrimengModule } from "./../primeng/primeng.module";
import { ProdutoModule } from "./../produto/produto.module";
import { SharedModule } from "./../shared/shared.module";
import { ItemRequisicaoFormComponent } from "./components/form/form.component";
import { ItemRequisicaoListaComponent } from "./components/lista/lista.component";

@NgModule({
    declarations: [ItemRequisicaoFormComponent, ItemRequisicaoListaComponent],
    imports: [CommonModule, SharedModule, PrimengModule, ProdutoModule],
    exports: [ItemRequisicaoFormComponent, ItemRequisicaoListaComponent],
})
export class ItemRequisicaoModule {}
