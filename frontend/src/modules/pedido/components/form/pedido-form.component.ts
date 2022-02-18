import { PedidoStepItensComponent } from './step-itens/pedido-step-itens.component';
import { PedidoStepInformacoesComponent } from './step-informacoes/pedido-step-informacoes.component';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { MenuItem, MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Pedido } from 'src/model/pedido';

import { PedidoStepMergeService } from '../../services/pedido-step-merge.service';
import { PedidoService } from '../../services/pedido.service';
import { Mensagens } from '../../../../utils/Mensagens';

@Component({
    selector: "pedido-form",
    templateUrl: "./pedido-form.component.html",
})
export class PedidoFormComponent implements OnInit {
    NOME_PAGINA = "Pedidos de Compra";
    registro: Pedido = {};
    itensStep: MenuItem[];

    constructor(
        private pedidoService: PedidoService,
        private messageService: MessageService,
        private router: Router,
        public stepMergeService: PedidoStepMergeService,
        public dynamicDialogRef: DynamicDialogRef,
        public configDialog: DynamicDialogConfig
    ) {}

    ngOnInit(): void {
        this.itensStep = [
            {
                label: "Informações",
            },
            {
                label: "Itens",
            },
        ];

        this.stepMergeService.state = { itens: [], dataPedido: new Date() };
    }

    onSubmit(formulario: NgForm): void {
        this.registro = this.stepMergeService.state;

        this.pedidoService.criar(this.registro).subscribe(() => {
            this.stepMergeService.state = {};
            this.messageService.add(Mensagens.SUCESSO_REGISTRO_SALVO);
            this.dynamicDialogRef.close();
        });
    }

    onLimpar(): void {}

}
