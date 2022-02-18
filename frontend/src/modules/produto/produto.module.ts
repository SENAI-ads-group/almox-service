import { ModalHistoricoComponent } from './components/modal-historico/modal-historico-produto.component';
import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";

import { PrimengModule } from "../primeng/primeng.module";
import { SharedModule } from "../shared/shared.module";
import { FornecedorModule } from "./../fornecedor/fornecedor.module";
import { ProdutoFiltroComponent } from "./components/filtro-busca/produto-filtro-busca.component";
import { ProdutoFormSectionComponent } from "./components/form-section/produto-form-section.component";
import { ProdutoFormComponent } from "./components/form/produto-form.component";
import { ProdutoBuscaComponent } from "./components/busca/produto-busca.component";
import { ProdutoModalListaComponent } from "./components/modal-listagem/produto-modal-lista.component";
import { ProdutoRoutingModule } from "./produto-routing.module";
import { ProdutoService } from "./services/produto.service";

@NgModule({
    declarations: [
        ProdutoBuscaComponent,
        ProdutoFiltroComponent,
        ProdutoFormComponent,
        ProdutoFormSectionComponent,
        ProdutoModalListaComponent,
        ModalHistoricoComponent
    ],
    imports: [
        CommonModule,
        ProdutoRoutingModule,
        PrimengModule,
        SharedModule,
        FornecedorModule,
    ],
    providers: [ProdutoService],
    exports: [ProdutoModalListaComponent],
})
export class ProdutoModule {}
