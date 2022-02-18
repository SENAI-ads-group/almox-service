import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Fabricante } from 'src/app/model/fabricante';
import { CommonService } from 'src/app/modules/shared/services/common.service';
import { HandleErrorService } from 'src/app/modules/shared/services/handle-error.service';
import { Mensagens } from 'src/app/utils/Mensagens';
import { rotaEstaEmModoVisualizacao } from 'src/app/utils/RouterUtil';

import { FabricanteService } from '../../services/fabricante.service';

@Component({
    selector: "fabricante-form",
    templateUrl: "./fabricante-form.component.html",
})
export class FabricanteFormComponent implements OnInit {
    fabricante: Fabricante = { contato: {} };

    tiposEndereco: any[];
    tiposTelefone: any[];

    editandoRegistroExistente: boolean;
    modoVisualizacao: boolean;
    @ViewChild("formulario") formulario: NgForm;

    constructor(
        private fabricanteService: FabricanteService,
        private handleErrorService: HandleErrorService,
        private messageService: MessageService,
        private commonService: CommonService,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.activatedRoute.params.subscribe(params => {
            const id: number = params["id"];
            if (id) {
                this.editandoRegistroExistente = true;
                this.fabricanteService.buscarPorId(id).subscribe(fabricante => {
                    this.fabricante = fabricante;
                });
            }
        });

        this.commonService.buscarEnumeradores().subscribe(enumaradores => {
            this.tiposEndereco = enumaradores.tiposEndereco;
            this.tiposTelefone = enumaradores.tiposTelefone;
        });

        this.modoVisualizacao = rotaEstaEmModoVisualizacao(
            this.activatedRoute.snapshot
        );
    }

    onSubmit(formulario: NgForm): void {
        if (!formulario.valid) return;

        const httpSubscriber = this.editandoRegistroExistente
            ? this.fabricanteService.atualizar(
                  this.fabricante.id,
                  this.fabricante
              )
            : this.fabricanteService.criar(this.fabricante);

        httpSubscriber.subscribe(() => {
            this.messageService.add(Mensagens.SUCESSO_REGISTRO_SALVO);
            this.router.navigate(["/fabricantes/"]);
        });
    }

    limpar(): void {
        this.fabricante;
    }

    habilitarModoEdicao = () =>
        this.router.navigate([`fabricantes/editar/${this.fabricante.id}`]);
}
