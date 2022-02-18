import { RequisicaoStepItensComponent } from './step-itens/requisicao-step-itens.component';
import { RequisicaoStepInformacoesComponent } from './step-informacoes/requisicao-step-informacoes.component';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { MenuItem, MessageService } from 'primeng/api';
import { DynamicDialogConfig, DynamicDialogRef } from 'primeng/dynamicdialog';
import { Requisicao } from 'src/model/requisicao';

import { RequisicaoStepMergeService } from '../../services/requisicao-step-merge.service';
import { RequisicaoService } from '../../services/requisicao.service';
import { Mensagens } from './../../../../utils/Mensagens';

@Component({
    selector: "departamento-form",
    templateUrl: "./requisicao-form.component.html",
})
export class RequisicaoFormComponent implements OnInit {
    NOME_PAGINA = "Requisições";
    registro: Requisicao = {};
    itensStep: MenuItem[];

    constructor(
        private requisicaoService: RequisicaoService,
        private messageService: MessageService,
        private router: Router,
        public stepMergeService: RequisicaoStepMergeService,
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

        this.stepMergeService.state = { itens: [], dataRequisicao: new Date() };
    }

    onSubmit(formulario: NgForm): void {
        this.registro = this.stepMergeService.state;

        this.requisicaoService.criar(this.registro).subscribe(() => {
            this.stepMergeService.state = {};
            this.messageService.add(Mensagens.SUCESSO_REGISTRO_SALVO);
            this.dynamicDialogRef.close();
        });
    }

    onLimpar(): void {}

}
