import { FornecedorService } from "./../../../../fornecedor/services/fornecedor.service";
import { Fornecedor } from "src/app/model/fornecedor";
import { UsuarioService } from "../../../../usuario/services/usuario.service";
import { DepartamentoService } from "src/app/modules/departamento/services/departamento.service";
import { Component, ViewChild, OnInit } from "@angular/core";
import { NgForm } from "@angular/forms";
import { ActivatedRoute, Router } from "@angular/router";
import { Observable } from "rxjs";
import { Departamento } from "src/app/model/departamento";

import { PedidoStepMergeService } from "../../../services/pedido-step-merge.service";

@Component({
    selector: "pedido-step-informacoes",
    templateUrl: "./pedido-step-informacoes.component.html",
    styleUrls: ["./pedido-step-informacoes.component.scss"],
})
export class PedidoStepInformacoesComponent implements OnInit {
    @ViewChild("formulario") formulario : NgForm;
    fornecedores$: Observable<Fornecedor[]>;

    constructor(
        private router: Router,
        private activatedRoute: ActivatedRoute,
        public stepMergeService: PedidoStepMergeService,
        private departamentoService: DepartamentoService,
        private fornecedorService: FornecedorService
    ) {}

    ngOnInit(): void {
        this.fornecedores$ = this.fornecedorService.buscarTodos();
    }

    onSubmit() {
        if (this.formulario.valid) {
            //this.stepMergeService.state.departamento = this.dados.departamento;
        }
    }

}
