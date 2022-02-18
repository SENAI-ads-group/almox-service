import { Mensagens } from '../../../utils/Mensagens';
import { Produto } from "../../../model/produto";
import { NgForm } from "@angular/forms";
import { Grupo } from "../../../model/grupo";
import { Component, OnInit, ViewChild } from "@angular/core";
import { GrupoService } from "../grupo.service";
import { CommonService } from "../../shared/services/common.service";
import { HandleErrorService } from "../../shared/services/handle-error.service";
import { ActivatedRoute, Router } from "@angular/router";
import { Message, MessageService } from "primeng/api";
import { rotaEstaEmModoVisualizacao } from "src/utils/RouterUtil";

@Component({
    selector: "grupo-form",
    templateUrl: "./grupo-form.component.html",
})
export class GrupoFormComponent implements OnInit {
    grupo: Grupo = {};
    descricao: String;
    produtos: Produto[];
    editandoRegistroExistente: boolean = false;

    constructor(
        private grupoService: GrupoService,
        private messageService: MessageService,
        private activatedRoute: ActivatedRoute,
        private router: Router
    ) {}

    ngOnInit(): void {
        this.activatedRoute.params.subscribe(params => {
            const id: number = params["id"];
            if (id) {
                this.editandoRegistroExistente = true;
                this.grupoService
                    .buscarPorId(id)
                    .subscribe(grupo => (this.grupo = grupo));
            }
        });
    }

    onSubmit(formulario: NgForm): void {
        if (!formulario.valid) return;

        const httpSubscriber = this.editandoRegistroExistente
            ? this.grupoService.atualizar(this.grupo.id, this.grupo)
            : this.grupoService.criar(this.grupo);

        httpSubscriber.subscribe(
            () => {
                this.messageService.add(Mensagens.SUCESSO_REGISTRO_SALVO);
                this.router.navigate(["/grupos/"]);
            }
        );
    }

    onLimpar(): void {
        this.grupo = {};
    }
}
