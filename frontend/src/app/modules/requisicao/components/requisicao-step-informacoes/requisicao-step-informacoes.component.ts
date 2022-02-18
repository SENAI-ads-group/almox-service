import { DepartamentoService } from "src/app/modules/departamento/services/departamento.service";
import { Component, ViewChild, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Observable } from "rxjs";
import { Departamento } from "src/app/model/departamento";

import { RequisicaoStepMergeService } from "./../../services/requisicao-step-merge.service";

@Component({
    selector: "requisicao-step-informacoes",
    templateUrl: "./requisicao-step-informacoes.component.html",
    styleUrls: ["./requisicao-step-informacoes.component.scss"],
})
export class RequisicaoStepInformacoesComponent implements OnInit {
    @ViewChild("formulario") formulario: NgForm;
    departamentos$: Observable<Departamento[]>;

    dados = {
        dataRequisicao: new Date(),
        departamento: null,
    };

    constructor(
        private router: Router,
        private activatedRoute: ActivatedRoute,
        private stepMergeService: RequisicaoStepMergeService,
        private departamentoService: DepartamentoService
    ) {}

    ngOnInit(): void {
        this.departamentos$ =
            this.departamentoService.buscarAssociadosUsuarioLogado();
    }

    onSubmit() {
        if (this.formulario.valid) {
            this.stepMergeService.state.departamento = this.dados.departamento;
            this.proximo();
        }
    }

    proximo() {
        this.router.navigate(["../itens"], { relativeTo: this.activatedRoute });
    }
}
