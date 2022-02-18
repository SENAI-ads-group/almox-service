import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";

import { RequisicaoBuscaComponent } from "./components/busca/requisicao-busca.component";
import { RequisicaoFormComponent } from "./components/form/requisicao-form.component";
import { RequisicaoAtendimentoComponent } from "./components/atendimento/requisicao-atendimento.component";
import { RequisicaoStepInformacoesComponent } from "./components/form/step-informacoes/requisicao-step-informacoes.component";
import { RequisicaoStepItensComponent } from "./components/form/step-itens/requisicao-step-itens.component";

const routes: Routes = [
    { path: "", component: RequisicaoBuscaComponent, pathMatch: "full" },
    { path: "informacoes/:id", component: RequisicaoAtendimentoComponent },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule],
})
export class RequisicaoRoutingModule {}
