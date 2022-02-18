
import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";

import { PrimengModule } from "./../primeng/primeng.module";
import { SharedModule } from "./../shared/shared.module";
import { RequisicaoBuscaComponent } from "./components/busca/requisicao-busca.component";
import { RequisicaoFiltroComponent } from "./components/filtro-busca/requisicao-filtro-busca.component";
import { RequisicaoFormComponent } from "./components/form/requisicao-form.component";
import { RequisicaoRoutingModule } from "./requisicao-routing.module";
<<<<<<< HEAD
import { RequisicaoStepInformacoesComponent } from './components/requisicao-step-informacoes/requisicao-step-informacoes.component';
import { RequisicaoStepItensComponent } from './components/requisicao-step-itens/requisicao-step-itens.component';
import { ItemRequisicaoModule } from '../item-requisicao/item_requisicao.module';
=======
import { RequisicaoStepInformacoesComponent } from "./components/form/step-informacoes/requisicao-step-informacoes.component";
import { RequisicaoStepItensComponent } from "./components/form/step-itens/requisicao-step-itens.component";
import { ItemRequisicaoModule } from "../item-requisicao/item-requisicao.module";
import { RequisicaoAtendimentoComponent } from "./components/atendimento/requisicao-atendimento.component";
>>>>>>> a7d33ac6e034c748fdf869da03c3fbb32b236fec

@NgModule({
    declarations: [
        RequisicaoBuscaComponent,
        RequisicaoFiltroComponent,
        RequisicaoFormComponent,
        RequisicaoStepInformacoesComponent,
        RequisicaoStepItensComponent,
        RequisicaoAtendimentoComponent,
    ],
    imports: [
        CommonModule,
        RequisicaoRoutingModule,
        PrimengModule,
        SharedModule,
<<<<<<< HEAD
        ItemRequisicaoModule
=======
        ItemRequisicaoModule,
>>>>>>> a7d33ac6e034c748fdf869da03c3fbb32b236fec
    ],
})
export class RequisicaoModule {}
