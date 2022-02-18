import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { PedidoBuscaComponent } from "./components/busca/pedido-busca.component";
import { PedidoFormComponent } from "./components/form/pedido-form.component";
import { PedidoAtendimentoComponent } from "./components/atendimento/pedido-atendimento.component";
import { PedidoStepInformacoesComponent } from "./components/form/step-informacoes/pedido-step-informacoes.component";
import { PedidoStepItensComponent } from "./components/form/step-itens/pedido-step-itens.component";

const routes: Routes = [
    { path: "", component: PedidoBuscaComponent, pathMatch: "full" },
    { path: "atendimento/:id", component: PedidoAtendimentoComponent },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class PedidoRoutingModule {}
