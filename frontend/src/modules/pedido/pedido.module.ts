import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

import { ItemPedidoModule } from '../item-pedido/item-pedido.module';
import { PrimengModule } from '../primeng/primeng.module';
import { SharedModule } from '../shared/shared.module';
import { PedidoAtendimentoComponent } from './components/atendimento/pedido-atendimento.component';
import { PedidoBuscaComponent } from './components/busca/pedido-busca.component';
import { PedidoFiltroComponent } from './components/filtro-busca/pedido-filtro-busca.component';
import { PedidoFormComponent } from './components/form/pedido-form.component';
import { PedidoStepInformacoesComponent } from './components/form/step-informacoes/pedido-step-informacoes.component';
import { PedidoStepItensComponent } from './components/form/step-itens/pedido-step-itens.component';
import { PedidoRoutingModule } from './pedido-routing.module';

@NgModule({
    declarations: [
        PedidoBuscaComponent,
        PedidoFiltroComponent,
        PedidoFormComponent,
        PedidoStepInformacoesComponent,
        PedidoStepItensComponent,
        PedidoAtendimentoComponent,
    ],
    imports: [
        CommonModule,
        PedidoRoutingModule,
        PrimengModule,
        SharedModule,
        ItemPedidoModule,
    ],
})
export class PedidoModule {}
